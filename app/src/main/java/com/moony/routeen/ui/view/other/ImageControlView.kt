package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.drawable.toBitmap
import com.moony.routeen.data.structure.other.ImageControlViewState
import com.moony.routeen.databinding.SourceCustomImageControlViewBinding
import com.moony.routeen.ui.view.memo.MovieMemoView
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

    private lateinit var parentLayout:ImageControlLayout
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

    constructor(context: Context, attrs: AttributeSet,state: ImageControlViewState) : super(context, attrs){
        val lp=LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        this.layoutParams=lp
        initView()
        setImageControlViewState(state)
    }




    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if(ev.actionMasked==MotionEvent.ACTION_DOWN){
            setFocusOn()

        }
        return super.onInterceptTouchEvent(ev)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.actionMasked){
            MotionEvent.ACTION_DOWN->{

                prevDragRawPositionX=event.rawX
                prevDragRawPositionY=event.rawY


                parentLayout=parentLayout
                parentWidth=parentLayout.width
                parentHeight=parentLayout.height


                return true
            }
            MotionEvent.ACTION_MOVE->{

                val diffX=event.rawX-prevDragRawPositionX
                val diffY=event.rawY-prevDragRawPositionY
                val x=this.x
                val y=this.y

                if((diffX+x)>0f &&(diffX+x+this.width<parentWidth))
                    this.x+=diffX
                if((diffY+y)>0f &&(diffY+y+this.height<parentHeight))
                    this.y+=diffY




                prevDragRawPositionX=event.rawX
                prevDragRawPositionY=event.rawY
            }
        }


        return super.onTouchEvent(event)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val p=this.parent
        Log.d("test","${p}")
        if(p is MovieMemoView){
            Log.d("its","movie")
        }
        if(p is ImageControlLayout)
            parentLayout=p
        else
            throw IllegalStateException("ImageControlView's parent must be ImageControlLayout")

        val constraintSet=ConstraintSet()
        constraintSet.clone(parentLayout)
        if(this.id==View.NO_ID)
            this.id= generateViewId()
        constraintSet.connect(this.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.TOP, )
        constraintSet.connect(this.id, ConstraintSet.BOTTOM, parentLayout.id, ConstraintSet.BOTTOM, )
        constraintSet.connect(this.id, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.LEFT, )
        constraintSet.connect(this.id, ConstraintSet.RIGHT, parentLayout.id, ConstraintSet.RIGHT, )

        constraintSet.applyTo(parentLayout)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {



        val lp=LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)

        binding = SourceCustomImageControlViewBinding.inflate(LayoutInflater.from(context), this)
        closeButton=binding.sourceCustomImageControlClose
        rotateButton=binding.sourceCustomImageControlRotate
        resizeButton=binding.sourceCustomImageControlResize
        mainImage=binding.sourceCustomImageControlImageView
        binding.sourceCustomImageControlClose.setOnClickListener{
            if(isFocus){
                (parent as ViewGroup).removeView(this)
            }

        }
        binding.sourceCustomImageControlRotate.setOnTouchListener { view, event ->


            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {


                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    //제 2 cos 법칙


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
                    this.getLocationOnScreen(list)

                    val rect=Rect()
                    this.getHitRect(rect)

                    resizeCenterX = rect.exactCenterX()
                    resizeCenterY = rect.exactCenterY()
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

                    val diffLen=length-prevResizeLen

                    val parentParams=this.layoutParams
                    val mainImageParams=mainImage.layoutParams


                    parentParams.width=(diffLen).toInt()*2+this.width
                    parentParams.height=(diffLen).toInt()*2+this.height
                    this.layoutParams=parentParams

                    mainImageParams.width=(diffLen).toInt()*2+mainImage.width
                    mainImageParams.height=(diffLen).toInt()*2+mainImage.height
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
        Log.d("ImageControlView","set focus off")
    }

    fun setFocusOn(){
        binding.sourceCustomImageControlClose.visibility= VISIBLE
        binding.sourceCustomImageControlResize.visibility= VISIBLE
        binding.sourceCustomImageControlRotate.visibility= VISIBLE
        binding.sourceCustomImageControlFrame.visibility= VISIBLE
        this.bringToFront()
        isFocus=true
        Log.d("ImageControlView,","set focus on")
    }


    private fun getDistanceBetweenTwoPosition(x1:Float,y1:Float,x2:Float,y2:Float):Float{
        return sqrt((x2-x1).pow(2)+(y2-y1).pow(2))
    }

    fun setImageControlViewState(state: ImageControlViewState){
        mainImage.setImageBitmap(state.bitmap)
        this.rotation=state.rotation
        val lp=this.layoutParams
        lp.width=state.width
        lp.height=state.height
        this.layoutParams=lp
        this.x=state.locationX
        this.y=state.locationY
    }

    fun getImageControlViewState():ImageControlViewState{

        return ImageControlViewState(
            (mainImage.drawable as BitmapDrawable).bitmap,
            this.rotation,
            this.x,
            this.y,
            this.width,
            this.height
        )
    }







}