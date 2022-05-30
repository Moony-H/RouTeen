package com.moony.routeen.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MemoDao {
    @Insert
    suspend fun insertMemoData(memo: Memo)

    @Delete
    suspend fun deleteMemoData(memo:Memo)

    @Query("SELECT * FROM Memo")
    suspend fun getAllMemoData():List<Memo>

    @Query("DELETE FROM Memo")
    suspend fun deleteAllMemoData()
}