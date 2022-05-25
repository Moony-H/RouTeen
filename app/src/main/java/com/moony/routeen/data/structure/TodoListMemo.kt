package com.moony.routeen.data.structure

import com.moony.routeen.data.MemoType

class TodoListMemo:Memo() {
    val test="only todo have"
    init{
        super.text="Todo"
        super.memoType=MemoType.TodoListMemo
    }
    fun getType():MemoType{
        return MemoType.TodoListMemo
    }
}