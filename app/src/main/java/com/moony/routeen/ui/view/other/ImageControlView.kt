package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.databinding.SourceCustomImageControlViewBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageControlView:ConstraintLayout {
    var isFocus = true
    private lateinit var binding: SourceCustomImageControlViewBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()
        initView()
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        binding = SourceCustomImageControlViewBinding.inflate(LayoutInflater.from(context), this)
        binding.sourceCustomImageControlClose.setOnClickListener {
            (parent as ViewGroup).removeView(this)
        }

        binding.sourceCustomImageControlRotate.setOnTouchListener { view, motionEvent ->
            Log.d("test","rotate button")
            true
        }

    }



    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN->{
                setFocusOn()
                Log.d("test","onTouch")
                return true
            }
            MotionEvent.ACTION_MOVE->{

                this.x=event.x+this.x-(this.width)/2
                this.y=event.y+this.y-(this.height)/2
            }
        }
        return super.onTouchEvent(event)
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



}