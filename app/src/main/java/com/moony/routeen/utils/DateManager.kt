package com.moony.routeen.utils

import java.text.SimpleDateFormat
import java.util.*

class DateManager {
    companion object{
        fun getTodayDate():String{
            return SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).toString()
        }
    }
}