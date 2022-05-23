package com.moony.routeen.di

import android.content.Context
import com.moony.routeen.data.AppDatabase
import com.moony.routeen.data.BusAlarmInfoDao
import com.moony.routeen.data.MemoDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase{
        return AppDatabase.getInstance(context)
    }
    @Provides
    fun provideBusAlarmInfoDao(appDatabase: AppDatabase):BusAlarmInfoDao{
        return appDatabase.busAlarmInfoDao()
    }

    @Provides
    fun provideMemoDataDao(appDatabase: AppDatabase):MemoDataDao{
        return appDatabase.memoDataDao()
    }
}