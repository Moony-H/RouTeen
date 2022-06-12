package com.moony.routeen.ui.view.memo

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.moony.routeen.R
import com.moony.routeen.RouTeenApplication
import com.moony.routeen.ui.view.other.ImageControlLayout

open class BaseMemoView: ImageControlLayout {

    constructor(context: Context):super(context){
        init()
    }
    constructor(context: Context,attrs:AttributeSet):super(context, attrs){
        init()
    }

    private fun init(){
        setBackgroundColor(ContextCompat.getColor(context,R.color.background))
    }

}