package com.moony.routeen.ui.view.other

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import com.moony.routeen.utils.Converter


class LinedEditText(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {
    private val mPaint: Paint = Paint()
    private val mRect=Rect()

    init{
        this.gravity=Gravity.START or Gravity.TOP
        mPaint.style=Paint.Style.FILL_AND_STROKE
        mPaint.color= Converter.stringColorToIntColor("#C0C0C0")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onDraw(canvas: Canvas) {
        //lineHeight=103
        //val baseLine=baseline+10
        //Log.d("line height","$lineHeight")
        //Log.d("text size","$textSize")
//
        //var lineCount=0
        //while(height>lineCount*lineHeight+baseLine){
        //    val y=(lineCount*lineHeight+baseLine).toFloat()
        //    canvas.drawLine(0f, y,width.toFloat(),y,mPaint)
        //    lineCount++
        //}
        //super.onDraw(canvas)
        val height = height
        val line_height = lineHeight

        var count = height / line_height

        if (lineCount > count) count = lineCount //for long text with scrolling


        val r: Rect = mRect
        val paint = mPaint
        var baseline = getLineBounds(0, r) //first line


        for (i in 0 until count) {
            canvas.drawLine(
                r.left.toFloat(), (baseline + 1).toFloat(),
                r.right.toFloat(), (baseline + 1).toFloat(), paint
            )
            baseline += lineHeight //next line
        }
        super.onDraw(canvas)

    }

}