package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType

class BasicMemoData:BaseMemoData() {
    var content=""
    init {
        super.memoType=MemoType.BasicMemo
    }
}