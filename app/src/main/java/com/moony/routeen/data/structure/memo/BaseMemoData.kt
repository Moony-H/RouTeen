package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState
import java.text.SimpleDateFormat
import java.util.*

open class BaseMemoData(
    var title: String,
    var date: String,
    var imageControlViewStateList:List<ImageControlViewState>
) {
    var memoType=MemoType.BaseMemo


}