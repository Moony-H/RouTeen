package com.moony.routeen.utils

import android.graphics.Color


class Converter {
    companion object{
        fun intColorToStringColor(intColor:Int):String{
            return String.format("#%06X", 0xFFFFFF and intColor)
        }

        fun stringColorToIntColor(stringColor:String):Int{
            return Color.parseColor(stringColor)
        }
    }
}