package com.moony.routeen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moony.routeen.data.Day

@Entity
data class BusAlarmInfo(
    var hour:Int,
    var minute:Int,
    var busNum:Int,
    var area:String,
    @PrimaryKey
    var day: Day
)