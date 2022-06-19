package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.data.structure.other.ImageControlViewState


open class ImageControlLayout:ConstraintLayout {

    constructor(context: Context):super(context){

    }
    constructor(context:Context,attrs: AttributeSet):super(context, attrs){

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if(this.id== View.NO_ID)
            this.id= generateViewId()
        for(i in 0 until childCount){
            val view=getChildAt(i)
            if(view.id==View.NO_ID)
                view.id= generateViewId()
        }
    }


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when(ev.actionMasked){
            MotionEvent.ACTION_DOWN->{
                setAllImageControlViewFocusOff()
            }
        }
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

    fun getAllImageControlViewState():List<ImageControlViewState>{
        val list= mutableListOf<ImageControlViewState>()
        val views=getAllImageControlView()
        views.forEach {
            list.add(it.getImageControlViewState())
        }
        return list
    }




}