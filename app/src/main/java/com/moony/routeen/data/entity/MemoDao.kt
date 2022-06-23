package com.moony.routeen.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


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

    @Query("SELECT * FROM Memo WHERE date=:date ORDER BY id")
    suspend fun getMemosByDate(date:String): Flow<List<Memo>>

}