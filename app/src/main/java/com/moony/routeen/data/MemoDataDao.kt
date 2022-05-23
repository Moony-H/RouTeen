package com.moony.routeen.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.moony.routeen.ui.view.memo.Memo

@Dao
interface MemoDataDao {
    @Insert
    suspend fun insertMemoData(memo: Memo)

    @Delete
    suspend fun deleteMemoData(memo:Memo)

    @Query("SELECT * FROM MemoData")
    suspend fun getAllMemoData():List<Memo>
}