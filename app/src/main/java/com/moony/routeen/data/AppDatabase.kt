package com.moony.routeen.data

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.moony.routeen.utils.RoomConverter
import com.moony.routeen.utils.RoomMemoConverter

@Database(entities = [BusAlarmInfo::class,MemoData::class],version = 0)
@TypeConverters(value = [RoomConverter::class,RoomMemoConverter::class])
abstract class AppDatabase:RoomDatabase() {
    abstract fun busAlarmInfoDao():BusAlarmInfoDao
    abstract fun memoDataDao():MemoDataDao
    companion object{
        @Volatile private var instance:AppDatabase?=null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            return instance?: synchronized(this){
                val temp=buildDatabase(context)
                instance=temp
                temp

            }


        }

        private fun buildDatabase(context: Context):AppDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app-database"
            ).addTypeConverter(RoomMemoConverter(Gson()))
                .build()


        }
    }

}