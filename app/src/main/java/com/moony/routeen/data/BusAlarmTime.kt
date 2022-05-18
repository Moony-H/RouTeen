package com.moony.routeen.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusAlarmTime(
    var hour:Int,
    var minute:Int,
    @PrimaryKey
    var day:Day
)