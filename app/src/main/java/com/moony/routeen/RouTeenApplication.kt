package com.moony.routeen

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RouTeenApplication:Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: RouTeenApplication
        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }

}