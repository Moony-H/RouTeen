package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.moony.routeen.R
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.databinding.SourceMemoBaseBinding
import com.moony.routeen.ui.view.other.ImageControlLayout
import com.moony.routeen.ui.view.other.ImageControlView

open class BaseMemoView: ImageControlLayout {

    private lateinit var binding:SourceMemoBaseBinding
    constructor(context: Context):super(context){
        initView()
    }

    constructor(context: Context,baseMemoData: BaseMemoData):super(context){
        setMemoData(baseMemoData)
        baseMemoData.imageControlViewStateList.forEach {
            val imageControlView=ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        initView()
    }
    constructor(context: Context,attrs:AttributeSet):super(context, attrs){
        initView()
    }
    constructor(context: Context,attrs:AttributeSet,baseMemoData: BaseMemoData):super(context,attrs){
        setMemoData(baseMemoData)
        baseMemoData.imageControlViewStateList.forEach {
            val imageControlView=ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        initView()
    }

    private fun initView(){
        binding=SourceMemoBaseBinding.inflate(LayoutInflater.from(context),this)

        setBackgroundColor(ContextCompat.getColor(context,R.color.memo_background))

    }


    open fun getMemoData(): BaseMemoData {

        return BaseMemoData(
            binding.sourceMemoBaseTitleEditText.text.toString(),
            binding.sourceMemoBaseDateTextView.text.toString(),
            getAllImageControlViewState()

        )
    }

    open fun setMemoData(data:BaseMemoData){
        binding.sourceMemoBaseDateTextView.text=data.date
        binding.sourceMemoBaseTitleEditText.setText(data.title)
    }

}