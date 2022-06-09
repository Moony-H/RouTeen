package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout


class ImageControlLayout:ConstraintLayout {

    constructor(context: Context):super(context)
    constructor(context:Context,attrs: AttributeSet):super(context, attrs){

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when(ev.actionMasked){
            MotionEvent.ACTION_DOWN->{
                setAllImageControlViewFocusOff()
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //if(event.actionMasked==MotionEvent.ACTION_DOWN){
        //    setAllImageControlViewFocusOff()
        //}
        return super.onTouchEvent(event)
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