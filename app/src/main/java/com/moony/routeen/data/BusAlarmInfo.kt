package com.moony.routeen.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusAlarmInfo(
    var hour:Int,
    var minute:Int,
    var busNum:Int,
    var area:String,
    @PrimaryKey
    var day:Day
)