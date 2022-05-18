package com.moony.routeen.data

import javax.inject.Inject

class BusAlarmInfoRepository @Inject constructor(private val busAlarmTimeDao: BusAlarmInfoDao) {
    suspend fun insertBusAlarmInfo(info: BusAlarmInfo){
        busAlarmTimeDao.insertBusAlarmInfo(info)
    }

    suspend fun deleteBusAlarmInfo(info:BusAlarmInfo){
        busAlarmTimeDao.deleteBusAlarmInfo(info)
    }

    suspend fun getBusAlarmInfoByDay(day: Day):List<BusAlarmInfo>{
        return busAlarmTimeDao.getBusAlarmInfoByDay(day)
    }
}