package com.moony.routeen.data.structure.memo

import com.moony.routeen.data.MemoType
import com.moony.routeen.data.structure.basic.Pair

class TodoListMemoData(): MemoData() {


    //밖에서 함부로 못건드리게
    private val _todoList= mutableListOf<CheckTextState>()
    val todoList:List<CheckTextState>
        get()=_todoList

    constructor(defaultSize:Int) : this() {
        for(i in 0 until defaultSize){
            _todoList.add(CheckTextState(false,""))
        }
    }

    init{
        super.memoType=MemoType.TodoListMemo

    }

    fun addTodoList(pair:CheckTextState){
        _todoList.add(pair)
    }

    fun popTodoList():CheckTextState{
        return _todoList.removeLast()
    }

    fun removeTodoListAt(index:Int){
        _todoList.removeAt(index)
    }

    fun setPairAt(index: Int,pair:CheckTextState){
        _todoList[index]=pair
    }

    fun moveTodoListComponent(targetIndex:Int,positionIndex:Int){
        val temp=_todoList.removeAt(targetIndex)
        _todoList.add(positionIndex,temp)
    }

    fun getSize():Int{
        return _todoList.size
    }

}