package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet

class TodoListMemoView:MemoView {
    constructor(context: Context):super(context){

    }
    constructor(context: Context,attrs:AttributeSet):super(context, attrs){

    }
    fun init(){
        super.text="ToDoListMemo"
    }
}