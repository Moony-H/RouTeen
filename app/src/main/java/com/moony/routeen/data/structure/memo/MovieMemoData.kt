package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState

class MovieMemoData(title: String, date: String,
                    imageControlViewStateList: List<ImageControlViewState>
) :BaseMemoData(title, date, imageControlViewStateList) {
    constructor(baseMemoData: BaseMemoData) : this(
        baseMemoData.title,
        baseMemoData.date,
        baseMemoData.imageControlViewStateList
    )
    var director=""
    var description=""
    var rating=0f
    var content=""
    init {
        super.memoType=MemoType.MovieMemo
    }
}