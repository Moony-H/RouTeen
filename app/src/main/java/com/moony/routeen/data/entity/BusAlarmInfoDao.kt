package com.moony.routeen.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.moony.routeen.data.Day

@Dao
interface BusAlarmInfoDao {
    @Insert
    suspend fun insertBusAlarmInfo(info: BusAlarmInfo)

    @Delete
    suspend fun deleteBusAlarmInfo(info: BusAlarmInfo)

    @Query("SELECT * FROM BusAlarmInfo WHERE day=:day ORDER BY hour")
    suspend fun getBusAlarmInfoByDay(day: Day):List<BusAlarmInfo>
}