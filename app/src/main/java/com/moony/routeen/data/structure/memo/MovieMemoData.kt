package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState

class MovieMemoData(
    title: String,
    date: String,
    imageControlViewStateList: List<ImageControlViewState>,
    var director:String,
    var description:String,
    var rating:Float,
    var content:String
) :BaseMemoData(title, date, imageControlViewStateList) {
    constructor(
        baseMemoData: BaseMemoData,
        director: String,
        description: String,
        rating: Float,
        content: String
    ) : this(
        baseMemoData.title,
        baseMemoData.date,
        baseMemoData.imageControlViewStateList,
        director,
        description,
        rating, content
    )

    init {
        super.memoType = MemoType.MovieMemo
    }
}