package com.moony.routeen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.BaseMemoData


@Entity
data class Memo(
    var baseMemoData: BaseMemoData,
    var memoType: MemoType
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}