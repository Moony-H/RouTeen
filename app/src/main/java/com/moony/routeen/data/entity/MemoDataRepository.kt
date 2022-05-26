package com.moony.routeen.data.entity

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoDataRepository @Inject constructor(private val memoDataDao: MemoDataDao){
    suspend fun insertMemoData(memo: MemoData){
        memoDataDao.insertMemoData(memo)
    }
    suspend fun deleteMemoData(memo: MemoData){
        memoDataDao.deleteMemoData(memo)
    }
    suspend fun getAllMemoData():List<MemoData>{
        return memoDataDao.getAllMemoData()
    }

    suspend fun deleteAllMemoData(){
        memoDataDao.deleteAllMemoData()
    }
}