package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.R
import com.moony.routeen.databinding.SourceCustomImageControlViewBinding
import kotlin.math.*

class ImageControlView:ConstraintLayout {
    var isFocus = true
    private lateinit var binding: SourceCustomImageControlViewBinding
    lateinit var closeButton:ImageView
    lateinit var rotateButton:ImageView
    lateinit var resizeButton:ImageView
    lateinit var mainImage:ImageView

    private var prevDragRawPositionX=0f
    private var prevDragRawPositionY=0f

    private var resizeCenterX=0f
    private var resizeCenterY=0f
    private var prevResizeLen=0f
    private var resizeDegree=0f

    private var widthHeightRatio=0f

    private var parentLayout:ImageControlLayout?=null
    private var parentWidth=0
    private var parentHeight=0

    constructor(context: Context) : super(context){
        val lp=LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        this.layoutParams=lp
        initView()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        val lp=LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        this.layoutParams=lp
        initView()
    }

    //constructor(context: Context,attrs: AttributeSet,defStyle:Int):super(context, attrs,defStyle){
    //    val lp=LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
    //    this.layoutParams=lp
    //}



    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if(ev.actionMasked==MotionEvent.ACTION_DOWN){
            setFocusOn()

        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("test","icv width: ${this.width}")
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("test","onTouchEvent")
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN->{
                Log.d("test","layout action down")
                prevDragRawPositionX=event.rawX
                prevDragRawPositionY=event.rawY
                val p=this.parent
                if(p is ImageControlLayout){
                    parentLayout=p
                    parentWidth=p.width
                    parentHeight=p.height
                }
                else{
                    throw IllegalStateException("ImageControlView's parent must be ImageControlLayout")
                }
                return true
            }
            MotionEvent.ACTION_MOVE->{

                val diffX=event.rawX-prevDragRawPositionX
                val diffY=event.rawY-prevDragRawPositionY
                val x=this.x
                val y=this.y
                parentLayout?.let {
                    if((diffX+x)>0f &&(diffX+x+this.width<parentWidth))
                        this.x+=diffX
                    if((diffY+y)>0f &&(diffY+y+this.height<parentHeight))
                        this.y+=diffY
                }?:run{
                    Log.d("what?","is null?")
                }



                prevDragRawPositionX=event.rawX
                prevDragRawPositionY=event.rawY
            }
        }


        return super.onTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {



        binding = SourceCustomImageControlViewBinding.inflate(LayoutInflater.from(context), this)
        closeButton=binding.sourceCustomImageControlClose
        rotateButton=binding.sourceCustomImageControlRotate
        resizeButton=binding.sourceCustomImageControlResize
        mainImage=binding.sourceCustomImageControlImageView

        binding.sourceCustomImageControlClose.setOnClickListener{
            Log.d("test","close clicked")
            if(isFocus){
                Log.d("test","click close")
                (parent as ViewGroup).removeView(this)
            }else{
                Log.d("test","focus off")
            }


        }
        binding.sourceCustomImageControlRotate.setOnTouchListener { view, event ->
            Log.d("test", "onTouch")

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("test", "rotate action down")

                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    //제 2 cos 법칙
                    Log.d("test","view location ${this.x}, ${this.y}")
                    val centerX = this.x + this.width / 2
                    val centerY = this.y + this.height / 2

                    val touchX = event.x + this.x + view.x
                    val touchY = event.y + this.y + view.y

                    val buttonX = this.x + view.x + view.width / 2
                    val buttonY = this.y + view.y + view.height / 2
                    val buttonAndTouchDistance = //a
                        getDistanceBetweenTwoPosition(buttonX, buttonY, touchX, touchY)
                    val centerAndButtonDistance = //b
                        getDistanceBetweenTwoPosition(centerX, centerY, buttonX, buttonY)
                    val centerAndTouchDistance = //c
                        getDistanceBetweenTwoPosition(centerX, centerY, touchX, touchY)

                    val vectorTouchX=touchX-centerX
                    val vectorTOuchY=touchY-centerY

                    val vectorButtonX=buttonX-centerX
                    val vectorButtonY=buttonY-centerY

                    //외적으로 각도 방향 구함
                    //안드로이드 내에선 외적의 부호의 반대임. 따라서 마이너스
                    val vectorProduct=-(vectorButtonX*vectorTOuchY-vectorButtonY*vectorTouchX)


                    //a^2 = b^2 + c^2 - 2bc*cosA
                    //cosA = (b^2 + c^2 - a^2) / (2bc)
                    val cosA =
                        (centerAndButtonDistance.pow(2)+centerAndTouchDistance.pow(2)-buttonAndTouchDistance.pow(2))/(2*centerAndButtonDistance*centerAndTouchDistance)
                    Log.d("test","cosA: $cosA")
                    Log.d("test","vectorProduct: $vectorProduct")
                    val currDegree=Math.toDegrees(acos(cosA).toDouble()).toFloat()
                    if(vectorProduct<0){
                        //시계 방향
                        this.rotation += currDegree
                    }
                    else{
                        //반시계 방향
                        this.rotation-=currDegree
                    }



                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.sourceCustomImageControlResize.setOnTouchListener{ view, event->
            when(event.actionMasked){
                MotionEvent.ACTION_DOWN->{
                    val list=IntArray(2)
                    view.getLocationOnScreen(list)

                    resizeCenterX = (list[0]+this.width / 2).toFloat()
                    resizeCenterY = (list[1]+ this.height / 2).toFloat()
                    resizeDegree=atan(abs(resizeCenterX-event.rawX)/abs(resizeCenterY-event.rawY))
                    prevResizeLen=getDistanceBetweenTwoPosition(
                        resizeCenterX,
                        resizeCenterY,
                        event.rawX,
                        event.rawY
                    )
                    true
                }
                MotionEvent.ACTION_MOVE->{







                    val length = getDistanceBetweenTwoPosition(
                        resizeCenterX,
                        resizeCenterY,
                        event.rawX,
                        event.rawY
                    )
                    Log.d("test","len $length")
                    Log.d("test","raw ${event.rawX} ${event.rawY}")
                    Log.d("test","center $resizeCenterX, $resizeCenterY")
                    val diffLen=length-prevResizeLen

                    val parentParams=this.layoutParams
                    val mainImageParams=mainImage.layoutParams


                    parentParams.width=(diffLen * cos(resizeDegree)).toInt()*2+this.width
                    parentParams.height=(diffLen* sin(resizeDegree)).toInt()*2+this.height
                    this.layoutParams=parentParams

                    mainImageParams.width=(diffLen * cos(resizeDegree)).toInt()*2+mainImage.width
                    mainImageParams.height=(diffLen* sin(resizeDegree)).toInt()*2+mainImage.height
                    mainImage.layoutParams=mainImageParams

                    prevResizeLen=length



                    true
                }
                else-> false
            }
        }


    }





    fun setFocusOff() {
        binding.sourceCustomImageControlClose.visibility= INVISIBLE
        binding.sourceCustomImageControlResize.visibility= INVISIBLE
        binding.sourceCustomImageControlRotate.visibility= INVISIBLE
        binding.sourceCustomImageControlFrame.visibility= INVISIBLE
        isFocus = false
        Log.d("test","set focus off")
    }

    fun setFocusOn(){
        binding.sourceCustomImageControlClose.visibility= VISIBLE
        binding.sourceCustomImageControlResize.visibility= VISIBLE
        binding.sourceCustomImageControlRotate.visibility= VISIBLE
        binding.sourceCustomImageControlFrame.visibility= VISIBLE
        this.bringToFront()
        isFocus=true
        Log.d("test,","set focus on")
    }

    private fun getTouchedButton(x:Float,y:Float):Int{
        val list=IntArray(2)
        closeButton.getLocationOnScreen(list)
        if(x<=list[0]+closeButton.width && x>=list[0]&&
                y<=list[1]+closeButton.width && y>=list[1]){
                    Log.d("test","is close")
            return closeButton.id
        }
        rotateButton.getLocationOnScreen(list)
        if(x<=list[0]+rotateButton.width && x>=list[0] &&
                y<=list[1]+rotateButton.height && y>=list[1]){
            return rotateButton.id
        }

        resizeButton.getLocationOnScreen(list)
        if(x<=list[0]+resizeButton.width && x>=list[0] &&
            y<=list[1]+resizeButton.height && y>=list[1]){
            return resizeButton.id
        }
        return -1
    }

    private fun getDistanceBetweenTwoPosition(x1:Float,y1:Float,x2:Float,y2:Float):Float{
        return sqrt((x2-x1).pow(2)+(y2-y1).pow(2))
    }







}