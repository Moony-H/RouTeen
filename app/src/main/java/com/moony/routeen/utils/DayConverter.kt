package com.moony.routeen.utils

import androidx.room.TypeConverter
import com.moony.routeen.data.Day
import java.lang.IllegalStateException

class DayConverter {
    @TypeConverter
    fun stringToDay(value:String):Day{
        val temp=value.split(" ")
        Day.values().forEach{
            if(temp[0]==it.day){
                return it
            }
        }
        throw IllegalStateException("day is not Exist")
    }
    @TypeConverter
    fun dayToString(value:Day):String{
        return "${value.day} ${value.num}"
    }
}