package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.databinding.SourceCustomImageControlViewBinding

class ImageControlView:ConstraintLayout {
    var isFocus = false
    private lateinit var binding: SourceCustomImageControlViewBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @RequiresApi(Build.VERSION_CODES.M)
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