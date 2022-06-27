package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.moony.routeen.R
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.databinding.SourceMemoBaseBinding
import com.moony.routeen.ui.view.other.ImageControlLayout
import com.moony.routeen.ui.view.other.ImageControlView

open class BaseMemoView: ImageControlLayout {

    private lateinit var binding:SourceMemoBaseBinding
    private var baseMemoData=BaseMemoData()
    constructor(context: Context):super(context){
        initView()
    }
    constructor(context: Context,baseMemoData: BaseMemoData):super(context){
        this.baseMemoData= baseMemoData
        baseMemoData.imageControlViewList.forEach {
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
        this.baseMemoData=baseMemoData
        baseMemoData.imageControlViewList.forEach {
            val imageControlView=ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        initView()
    }

    private fun initView(){
        binding=SourceMemoBaseBinding.inflate(LayoutInflater.from(context),this)
        setData()
        setBackgroundColor(ContextCompat.getColor(context,R.color.memo_background))

    }
    private fun setData(){
        binding.sourceMemoBaseDateTextView.text=baseMemoData.date
        binding.sourceMemoBaseTitleEditText.setText(baseMemoData.title)
    }

    open fun getMemoData():BaseMemoData{
        this.baseMemoData.title=binding.sourceMemoBaseTitleEditText.text.toString()
        this.baseMemoData.imageControlViewList=this.getAllImageControlViewState()
        this.baseMemoData.date=binding.sourceMemoBaseDateTextView.text.toString()
        return this.baseMemoData
    }

    open fun setMemoData(data:BaseMemoData){
        this.baseMemoData=data
        setData()
    }

}