package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.BasicMemoData
import com.moony.routeen.databinding.SourceMemoBasicBinding

class BasicMemoView: BaseMemoView {


    private lateinit var binding:SourceMemoBasicBinding
    constructor(context: Context) : super(context){
        initView()
    }
    constructor(context: Context,data: BasicMemoData):super(context,data){
        memoData=data
        initView()

    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        initView()
    }
    constructor(context: Context,attrs:AttributeSet,data:BasicMemoData):super(context, attrs,data){
        memoData=data
        initView()

    }


    private fun initView() {
        binding=SourceMemoBasicBinding.inflate(LayoutInflater.from(context), this)
        binding.sourceMemoBasicContent.setText(basicMemoData.content)
    }

    override fun getMemo(): BaseMemoData {
        super.getMemo()
        Log.d("test","super: ${super.memoData.title}")
        Log.d("test","this: ${this.memoData.title}")
        this.basicMemoData.content=binding.sourceMemoBasicContent.text.toString()
        return this.memoData
    }

    override fun setMemo(data: BaseMemoData) {
        super.setMemo(data)
        if(data is BasicMemoData){
            this.memoData=data
            binding.sourceMemoBasicContent.setText(data.content)
        }
    }





}