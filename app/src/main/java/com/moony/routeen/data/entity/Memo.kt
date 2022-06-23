package com.moony.routeen.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.BaseMemoData
import java.text.SimpleDateFormat
import java.util.*


@Entity(ignoredColumns = ["imageControlViewList"])
data class Memo(
    var baseMemoData: BaseMemoData,
    var date: String
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}