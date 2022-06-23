package com.moony.routeen.data.structure.other

class DateFormat(private val year:Int, private val month:Int, private val day:Int) {
    var date:String=""
    init {

        val monthString = if(month<10){
            "0$month"
        }else{
            "$month"
        }
        val dayString=if(day<10){
            "0$day"
        }else{
            "$day"
        }
        date="$year.$monthString.$dayString"
    }
}