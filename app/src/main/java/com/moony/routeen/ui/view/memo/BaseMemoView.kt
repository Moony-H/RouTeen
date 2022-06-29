package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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
    protected open var memoData=BaseMemoData()
    constructor(context: Context):super(context){
        memoData=BaseMemoData()
        initView()
    }

    constructor(context: Context,baseMemoData: BaseMemoData):super(context){
        this.memoData= baseMemoData
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
        this.memoData=baseMemoData
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
        binding.sourceMemoBaseDateTextView.text=memoData.date
        binding.sourceMemoBaseTitleEditText.setText(memoData.title)
    }

    open fun getMemo():BaseMemoData{
        this.memoData.title=binding.sourceMemoBaseTitleEditText.text.toString()
        this.memoData.imageControlViewList=this.getAllImageControlViewState()
        this.memoData.date=binding.sourceMemoBaseDateTextView.text.toString()
        Log.d("test","base memo data title: ${memoData.title}")
        return this.memoData
    }

    open fun setMemo(data:BaseMemoData){
        this.memoData=data
        setData()
    }

}