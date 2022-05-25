package com.moony.routeen.utils

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.Memo
import com.moony.routeen.data.structure.TodoListMemo

@ProvidedTypeConverter
class RoomMemoConverter(private val gson: Gson) {
    @TypeConverter
    fun memoToJson(memo: Memo): String{
        //if(memo.memoType==MemoType.TodoListMemo){
        //    Log.d("DataBase","save TodoListMemo")
        //    return gson.toJson(memo as TodoListMemo)
        //}

        return gson.toJson(memo)
    }

    @TypeConverter
    fun jsonToMemo(json:String): Memo {
        val temp=gson.fromJson(json,Memo::class.java)
        Log.d("test",json)
        if(temp.memoType==MemoType.TodoListMemo){
            return gson.fromJson(json,TodoListMemo::class.java)
        }

        return temp
    }
}