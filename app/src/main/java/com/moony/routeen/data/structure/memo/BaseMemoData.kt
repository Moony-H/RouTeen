package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.other.ImageControlViewState
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseMemoData {
    var memoType=MemoType.BaseMemo
    var title=""
    var imageControlViewList= mutableListOf<ImageControlViewState>()
    var date= SimpleDateFormat("yyyy.mm.dd",Locale.KOREA).format(Date())
}