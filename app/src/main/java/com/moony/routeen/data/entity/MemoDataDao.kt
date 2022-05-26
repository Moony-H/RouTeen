package com.moony.routeen.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MemoDataDao {
    @Insert
    suspend fun insertMemoData(memo: MemoData)

    @Delete
    suspend fun deleteMemoData(memo:MemoData)

    @Query("SELECT * FROM MemoData")
    suspend fun getAllMemoData():List<MemoData>

    @Query("DELETE FROM MemoData")
    suspend fun deleteAllMemoData()
}