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

    private lateinit var baseBinding: SourceMemoBaseBinding

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, baseMemoData: BaseMemoData) : super(context) {
        baseMemoData.imageControlViewStateList.forEach {
            val imageControlView = ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, baseMemoData: BaseMemoData) : super(
        context,
        attrs
    ) {
        baseMemoData.imageControlViewStateList.forEach {
            val imageControlView = ImageControlView(context)
            imageControlView.setImageControlViewState(it)
            this.addView(imageControlView)
        }
        initView()
    }

    private fun initView() {
        baseBinding = SourceMemoBaseBinding.inflate(LayoutInflater.from(context), this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.memo_background))

    }


    open fun getMemoData(): BaseMemoData {

        return BaseMemoData(
            baseBinding.sourceMemoBaseTitleEditText.text.toString(),
            baseBinding.sourceMemoBaseDateTextView.text.toString(),
            getAllImageControlViewState()

        )
    }

    open fun setMemoData(data: BaseMemoData) {
        baseBinding.sourceMemoBaseDateTextView.text = data.date
        baseBinding.sourceMemoBaseTitleEditText.setText(data.title)
    }

}