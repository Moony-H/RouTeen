package com.moony.routeen.data

import com.moony.routeen.ui.view.memo.Memo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoDataRepository @Inject constructor(private val memoDataDao: MemoDataDao){
    suspend fun insertMemoData(memo: Memo){
        memoDataDao.insertMemoData(memo)
    }
    suspend fun deleteMemoData(memo: Memo){
        memoDataDao.deleteMemoData(memo)
    }
    suspend fun getAllMemoData():List<Memo>{
        return memoDataDao.getAllMemoData()
    }
}