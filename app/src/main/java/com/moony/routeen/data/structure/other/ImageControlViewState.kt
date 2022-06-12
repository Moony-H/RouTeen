package com.moony.routeen.data.structure.other

import android.graphics.Bitmap

data class ImageControlViewState(
    var bitmap: Bitmap,
    var rotation:Float,
    var locationX:Float,
    var locationY:Float,
    var width:Int,
    var height:Int
)