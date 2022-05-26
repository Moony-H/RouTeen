package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.data.structure.memo.TodoListMemo
import com.moony.routeen.ui.view.other.CheckTextView

class TodoListMemoView:MemoView {
    var todoListMemo=TodoListMemo()
    constructor(context: Context):super(context){

    }
    constructor(context: Context,attrs:AttributeSet):super(context, attrs){

    }

    fun addEmptyTodoListComponent(){
        todoListMemo.addTodoList(Pair(false , ""))
        val checkTextView=CheckTextView(this.context)
        val layoutParams=LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.leftToLeft=LayoutParams.PARENT_ID
        layoutParams.rightToRight=LayoutParams.PARENT_ID
        checkTextView.layoutParams=layoutParams
        this.addView(checkTextView)
    }



}