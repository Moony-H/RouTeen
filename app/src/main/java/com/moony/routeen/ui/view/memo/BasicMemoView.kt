package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.databinding.SourceCustomBasicMemoBinding

class BasicMemoView: BaseMemoView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()
        initView()
    }

    private fun initView() {
        SourceCustomBasicMemoBinding.inflate(LayoutInflater.from(context), this)

    }
}