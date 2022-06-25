package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState
import java.text.SimpleDateFormat
import java.util.*

open class BaseMemoData {
    var memoType=MemoType.BaseMemo
    var title=""
    var imageControlViewList= mutableListOf<ImageControlViewState>()
    var date: String = SimpleDateFormat("yyyy.mm.dd",Locale.KOREA).format(Date())
}