package com.moony.routeen.data.entity

import com.moony.routeen.data.Day
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusAlarmInfoRepository @Inject constructor(private val busAlarmTimeDao: BusAlarmInfoDao) {
    suspend fun insertBusAlarmInfo(info: BusAlarmInfo){
        busAlarmTimeDao.insertBusAlarmInfo(info)
    }

    suspend fun deleteBusAlarmInfo(info: BusAlarmInfo){
        busAlarmTimeDao.deleteBusAlarmInfo(info)
    }

    suspend fun getBusAlarmInfoByDay(day: Day):List<BusAlarmInfo>{
        return busAlarmTimeDao.getBusAlarmInfoByDay(day)
    }
}