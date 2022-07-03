package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState

class BasicMemoData(
    title: String,
    date: String,
    imageControlViewStateList: List<ImageControlViewState>,
    var content:String
) :BaseMemoData(title, date, imageControlViewStateList) {
    constructor(baseMemoData: BaseMemoData, content: String) : this(
        baseMemoData.title,
        baseMemoData.date,
        baseMemoData.imageControlViewStateList,
        content
    )

    init {
        super.memoType = MemoType.BasicMemo
    }
}