package com.moony.routeen.utils

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.MemoData
import com.moony.routeen.data.structure.memo.TodoListMemoData

@ProvidedTypeConverter
class RoomMemoConverter(private val gson: Gson) {
    @TypeConverter
    fun memoToJson(memoData: MemoData): String{
        //if(memo.memoType==MemoType.TodoListMemo){
        //    Log.d("DataBase","save TodoListMemo")
        //    return gson.toJson(memo as TodoListMemo)
        //}

        return gson.toJson(memoData)
    }

    @TypeConverter
    fun jsonToMemo(json:String): MemoData {
        val temp=gson.fromJson(json, MemoData::class.java)
        Log.d("test",json)
        if(temp.memoType==MemoType.TodoListMemo){
            return gson.fromJson(json, TodoListMemoData::class.java)
        }

        return temp
    }
}