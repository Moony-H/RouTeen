package com.moony.routeen.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.moony.routeen.R
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String

@AndroidEntryPoint
class MainActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a=View(baseContext)
        Log.d("test","${Color.parseColor("#AAFFFF")}")

    }
}