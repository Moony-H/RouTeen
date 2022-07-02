package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState

class BasicMemoData(title: String, date: String,
                    imageControlViewStateList: List<ImageControlViewState>
) :BaseMemoData(title, date, imageControlViewStateList) {
    constructor(baseMemoData: BaseMemoData) : this(
        baseMemoData.title,
        baseMemoData.date,
        baseMemoData.imageControlViewStateList
    )
    var content=""
    init {
        super.memoType=MemoType.BasicMemo
    }
}