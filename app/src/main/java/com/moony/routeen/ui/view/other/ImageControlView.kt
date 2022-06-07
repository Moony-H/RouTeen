package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.moony.routeen.R
import com.moony.routeen.databinding.SourceCustomImageControlViewBinding
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.math.acos
import kotlin.math.acosh
import kotlin.math.pow
import kotlin.math.sqrt

class ImageControlView:ConstraintLayout {
    var isFocus = true
    private lateinit var binding: SourceCustomImageControlViewBinding
    lateinit var closeButton:ImageView
    lateinit var rotateButton:ImageView
    lateinit var resizeButton:ImageView
    private var prevDegree=0.0f
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()
        initView()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        setFocusOn()
        return super.onInterceptTouchEvent(ev)
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN->{
                Log.d("test","focus on")
                val parent=this.parent
                if(parent is ImageControlLayout){
                    //Log.d("test","set All Off")
                    //parent.setAllImageControlViewFocusOff()
                }else{
                    throw IllegalArgumentException("ImageControlView's parent must be ImageControlLayout")
                }

                return true
            }
            MotionEvent.ACTION_MOVE->{

                this.x=event.x+this.x-(this.width)/2
                this.y=event.y+this.y-(this.height)/2
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
        //GlobalScope.launch(Dispatchers.Main) {
        //    while(true){
        //        this@ImageControlView.rotation+=45.0F
        //        Log.d("test","rotation: ${this@ImageControlView.rotation}")
        //        delay(1000)
        //    }
//
        //}


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
            Log.d("test","click resize")
            false
        }


    }





    fun setFocusOff() {
        binding.sourceCustomImageControlClose.visibility= GONE
        binding.sourceCustomImageControlResize.visibility= GONE
        binding.sourceCustomImageControlRotate.visibility= GONE
        binding.sourceCustomImageControlFrame.visibility= GONE
        isFocus = false
    }

    fun setFocusOn(){
        binding.sourceCustomImageControlClose.visibility= VISIBLE
        binding.sourceCustomImageControlResize.visibility= VISIBLE
        binding.sourceCustomImageControlRotate.visibility= VISIBLE
        binding.sourceCustomImageControlFrame.visibility= VISIBLE
        isFocus=true
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