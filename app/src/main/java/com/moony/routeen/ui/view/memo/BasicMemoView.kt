package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.BasicMemoData
import com.moony.routeen.databinding.SourceMemoBasicBinding

class BasicMemoView: BaseMemoView {


    private lateinit var binding: SourceMemoBasicBinding

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, data: BasicMemoData) : super(context, data) {
        setMemoData(data)
        initView()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, data: BasicMemoData) : super(
        context,
        attrs,
        data
    ) {
        setMemoData(data)
        initView()

    }


    private fun initView() {
        binding = SourceMemoBasicBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun getMemoData(): BaseMemoData {
        return BasicMemoData(super.getMemoData(), binding.sourceMemoBasicContent.text.toString())
    }

    override fun setMemoData(data: BaseMemoData) {
        super.setMemoData(data)
        if (data is BasicMemoData)
            binding.sourceMemoBasicContent.setText(data.content)

    }


}