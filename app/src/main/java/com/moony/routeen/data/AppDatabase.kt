package com.moony.routeen.data

import android.content.Context
import androidx.room.*

@Database(entities = [BusAlarmTime::class],version = 0)
abstract class AppDatabase:RoomDatabase() {
    abstract fun busAlarmTimeDao():BusAlarmTimeDao
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
            ).build()


        }
    }

}