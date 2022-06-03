package com.moony.routeen.ui.view.memo

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.R
import com.moony.routeen.RouTeenApplication

open class BaseMemoView: ConstraintLayout {

    constructor(context: Context):super(context){

    }
    constructor(context: Context,attrs:AttributeSet):super(context, attrs){

    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun init(){
        setBackgroundColor(RouTeenApplication.getApplicationContext().getColor(R.color.background))
    }

}