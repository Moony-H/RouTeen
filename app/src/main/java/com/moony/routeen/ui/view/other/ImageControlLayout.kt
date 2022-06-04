package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.math.abs

class ImageControlLayout:ConstraintLayout {

    private var isFirst=true
    constructor(context: Context):super(context)
    constructor(context:Context,attrs: AttributeSet):super(context, attrs){

    }
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        //when(ev.actionMasked){
        //    MotionEvent.ACTION_DOWN->{
        //        Log.d("test","intercept focus off")
        //        setAllImageControlViewFocusOff()
        //        return false
        //    }
        //}
        return super.onInterceptTouchEvent(ev)
    }




    fun getAllImageControlView():List<ImageControlView>{
        val list= mutableListOf<ImageControlView>()
        for(i in 0 until childCount){
            val c=getChildAt(i)
            if(c is ImageControlView)
                list.add(c)
        }
        return list
    }

    fun setAllImageControlViewFocusOff(){
        for(i in 0 until childCount){
            val c=getChildAt(i)
            if(c is ImageControlView){
                if(c.isFocus){
                    c.setFocusOff()
                }
            }
        }
    }




}