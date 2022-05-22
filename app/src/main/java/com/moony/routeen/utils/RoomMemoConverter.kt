package com.moony.routeen.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moony.routeen.data.MemoType
import com.moony.routeen.ui.view.memo.Memo
import java.util.*
@ProvidedTypeConverter
class RoomMemoConverter(private val gson: Gson) {
    @TypeConverter
    fun memoToJson(memo: Memo): String{
        return gson.toJson(memo)
    }

    @TypeConverter
    fun jsonToMemo(json:String): Memo {
        return gson.fromJson(json,Memo::class.java)
    }
}