package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.data.structure.memo.BasicMemoData
import com.moony.routeen.databinding.SourceMemoBasicBinding

class BasicMemoView: BaseMemoView {
    private var basicMemoData=BasicMemoData()
    private lateinit var binding:SourceMemoBasicBinding
    constructor(context: Context) : super(context){
        initView()
    }
    constructor(context: Context,data: BasicMemoData):super(context,data){
        basicMemoData=data
        initView()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        initView()
    }
    constructor(context: Context,attrs:AttributeSet,data:BasicMemoData):super(context, attrs,data){
        basicMemoData=data
        initView()

    }


    private fun initView() {
        binding=SourceMemoBasicBinding.inflate(LayoutInflater.from(context), this)

        binding.sourceBasicMemoDateTextView.text= basicMemoData.date
        binding.sourceMemoBasicTitleEditText.setText(basicMemoData.title)
        binding.sourceMemoBasicContent.setText(basicMemoData.content)
    }
    fun getBasicMemoViewData():BasicMemoData{
        basicMemoData.content=binding.sourceMemoBasicContent.text.toString()
        basicMemoData.title=binding.sourceMemoBasicTitleEditText.text.toString()
        return basicMemoData
    }



}