package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState
import java.util.*

abstract class BaseMemoData {
    var memoType=MemoType.BasicMemo
    var title=""
    var imageControlViewList= mutableListOf<ImageControlViewState>()
    var date= Date()
}