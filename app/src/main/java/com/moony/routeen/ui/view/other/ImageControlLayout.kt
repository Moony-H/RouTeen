package com.moony.routeen.ui.view.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.math.abs

class ImageControlLayout:ViewGroup {
    private lateinit var mViewDragHelper:ViewDragHelper
    constructor(context: Context):super(context)
    constructor(context:Context,attrs: AttributeSet):super(context, attrs){
        mViewDragHelper= ViewDragHelper.create(this,0.1f,ViewDragHelperCallBack())
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        //단 한번이라도 return true를 하면 여기서는 MOVE,Up 을 처리하지 않는다.
        return mViewDragHelper.shouldInterceptTouchEvent(ev)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        super.onLayout(changed, l, t, r, b)
    }
    override fun onDraw(canvas: Canvas?) {
        Log.d("on","draw")
        super.onDraw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //return true 를 해야 ViewDragHelper 가 동작한다.
        mViewDragHelper.processTouchEvent(event)
        return true
    }


    override fun computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    inner class ViewDragHelperCallBack: ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            if(child is ImageControlView){
                child.setFocusOn()
                return true
            }
            else{
                Log.d("test","is not control view")
                val ICL=this@ImageControlLayout
                for(i in 0 until ICL.childCount){
                    val c=ICL.getChildAt(i)
                    if(c is ImageControlView){
                        c.setFocusOff()
                    }
                }
                return false
            }

        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }



        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            Log.d("test","onRelease")
            super.onViewReleased(releasedChild, xvel, yvel)
        }



    }


}