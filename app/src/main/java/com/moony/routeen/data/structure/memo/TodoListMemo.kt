package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.memo.Memo

class TodoListMemo: Memo() {

    //밖에서 함부로 못건드리게
    private val _todoList= mutableListOf<Pair<Boolean,String>>()
    val todoList:List<Pair<Boolean,String>>
        get()=_todoList


    init{
        super.memoType=MemoType.TodoListMemo

    }

    fun addTodoList(pair:Pair<Boolean,String>){
        _todoList.add(pair)
    }

    fun popTodoList():Pair<Boolean,String>{
        return _todoList.removeLast()
    }

    fun removeTodoListAt(index:Int){
        _todoList.removeAt(index)
    }

    fun setPairAt(index: Int,pair:Pair<Boolean,String>){
        _todoList[index]=pair
    }

    fun moveTodoListComponent(targetIndex:Int,positionIndex:Int){
        val temp=_todoList.removeAt(targetIndex)
        _todoList.add(positionIndex,temp)
    }

}