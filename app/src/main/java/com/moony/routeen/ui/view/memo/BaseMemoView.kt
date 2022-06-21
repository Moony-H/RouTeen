package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.moony.routeen.R
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.ui.view.other.ImageControlLayout
import com.moony.routeen.ui.view.other.ImageControlView

open class BaseMemoView: ImageControlLayout {


    constructor(context: Context):super(context){
        init()
    }
    constructor(context: Context,baseMemoData: BaseMemoData):super(context){
        baseMemoData.imageControlViewList.forEach {
            val imageControlView=ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        init()
    }
    constructor(context: Context,attrs:AttributeSet):super(context, attrs){
        init()
    }
    constructor(context: Context,attrs:AttributeSet,baseMemoData: BaseMemoData):super(context,attrs){
        baseMemoData.imageControlViewList.forEach {
            val imageControlView=ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        init()
    }

    private fun init(){
        setBackgroundColor(ContextCompat.getColor(context,R.color.memo_background))

    }

}