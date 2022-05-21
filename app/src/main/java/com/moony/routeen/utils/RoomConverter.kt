package com.moony.routeen.utils

import android.graphics.Color
import androidx.room.TypeConverter
import com.moony.routeen.data.Day
import java.util.*

class RoomConverter {
    @TypeConverter
    fun stringToDay(value:String):Day{
        val temp=value.split(" ")
        return Day.values()[temp[1].toInt()]
    }
    @TypeConverter
    fun dayToString(value:Day):String{
        return "${value.dayName} ${value.num}"
    }

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

}