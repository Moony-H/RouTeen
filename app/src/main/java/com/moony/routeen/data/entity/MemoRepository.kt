package com.moony.routeen.data.entity

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoRepository @Inject constructor(private val memoDao: MemoDao){
    suspend fun insertMemo(memo: Memo){
        memoDao.insertMemoData(memo)
    }
    suspend fun deleteMemo(memo: Memo){
        memoDao.deleteMemoData(memo)
    }
    suspend fun getAllMemo():List<Memo>{
        return memoDao.getAllMemoData()
    }

    suspend fun deleteAllMemo(){
        memoDao.deleteAllMemoData()
    }
}