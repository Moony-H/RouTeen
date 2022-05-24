package com.moony.routeen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.Memo


@Entity
data class MemoData(
    var memo: Memo,
    var memoType: MemoType
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}