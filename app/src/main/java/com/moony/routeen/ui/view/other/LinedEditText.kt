package com.moony.routeen.ui.view.other

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import com.moony.routeen.utils.Converter


class LinedEditText(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {
    private val mPaint: Paint = Paint()

    init{
        this.gravity=Gravity.START or Gravity.TOP
        mPaint.style=Paint.Style.FILL_AND_STROKE
        mPaint.color= Converter.stringColorToIntColor("#C0C0C0")
    }


    override fun onDraw(canvas: Canvas) {

        val baseLine=baseline


        var lineCount=0
        while(height>lineCount*lineHeight+baseLine){
            val y=(lineCount*lineHeight+baseLine).toFloat()
            canvas.drawLine(0f, y,width.toFloat(),y,mPaint)
            lineCount++
        }


        super.onDraw(canvas)
    }

}