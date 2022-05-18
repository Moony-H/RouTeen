package com.moony.routeen.data

import android.graphics.Color
import androidx.room.Entity
import java.util.*

@Entity
data class Memo(
    var date:Date,
    var title:String,
    var content:String,
    var color: Color
)