package com.moony.routeen.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface BusAlarmTimeDao {
    @Insert
    suspend fun insertBusTime(time: BusAlarmTime)

    @Delete
    suspend fun deleteBusTime(time: BusAlarmTime)
}