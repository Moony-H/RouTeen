package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState

class BasicMemoData(title: String, date: String,
                    imageControlViewStateList: List<ImageControlViewState>
) :BaseMemoData(title, date, imageControlViewStateList) {
    var content=""
    init {
        super.memoType=MemoType.BasicMemo
    }
}