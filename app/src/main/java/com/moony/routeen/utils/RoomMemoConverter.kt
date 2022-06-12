package com.moony.routeen.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.BasicMemoData
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.TodoListMemoData

@ProvidedTypeConverter
class RoomMemoConverter(private val gson: Gson) {
    @TypeConverter
    fun memoToJson(baseMemoData: BaseMemoData): String{
        //if(memo.memoType==MemoType.TodoListMemo){
        //    Log.d("DataBase","save TodoListMemo")
        //    return gson.toJson(memo as TodoListMemo)
        //}

        return gson.toJson(baseMemoData)
    }

    @TypeConverter
    fun jsonToMemo(json:String): BaseMemoData {
        val temp=gson.fromJson(json, BaseMemoData::class.java)

        if(temp.memoType==MemoType.TodoListMemo)
            return gson.fromJson(json, TodoListMemoData::class.java)
        else if (temp.memoType==MemoType.BasicMemo)
            return gson.fromJson(json, BasicMemoData::class.java)

        return temp
    }
}