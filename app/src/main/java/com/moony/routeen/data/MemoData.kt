package com.moony.routeen.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moony.routeen.ui.view.memo.Memo

@Entity
data class MemoData(
    var type:Int,
    var memo:Memo
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}