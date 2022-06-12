package com.moony.routeen.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.BasicMemoData
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.MovieMemoData
import com.moony.routeen.data.structure.memo.TodoListMemoData

@ProvidedTypeConverter
class RoomMemoConverter(private val gson: Gson) {
    @TypeConverter
    fun memoToJson(baseMemoData: BaseMemoData): String{

        return gson.toJson(baseMemoData)
    }

    @TypeConverter
    fun jsonToMemo(json:String): BaseMemoData {
        val temp=gson.fromJson(json, BaseMemoData::class.java)

        return when(temp.memoType){
            MemoType.BasicMemo->{
                gson.fromJson(json, BasicMemoData::class.java)
            }
            MemoType.TodoListMemo->{
                gson.fromJson(json, TodoListMemoData::class.java)
            }
            MemoType.MovieMemo->{
                gson.fromJson(json, MovieMemoData::class.java)
            }
            else->temp
        }

    }
}