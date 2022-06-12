package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType

class MovieMemoData:BaseMemoData() {
    var director=""
    var description=""
    var rating=0f
    var content=""
    init {
        super.memoType=MemoType.MovieMemo
    }
}