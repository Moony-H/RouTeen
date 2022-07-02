package com.moony.routeen.ui.view.memo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.TodoListMemoData

import com.moony.routeen.databinding.SourceMemoTodoListBinding

import com.moony.routeen.ui.adapter.RecyclerviewSwipeHelper
import com.moony.routeen.ui.adapter.TodoListMemoViewAdapter
import com.moony.routeen.utils.DateManager

class TodoListMemoView:BaseMemoView {

    private lateinit var todoListMemoData:TodoListMemoData
    private lateinit var binding: SourceMemoTodoListBinding
    private lateinit var adapter: TodoListMemoViewAdapter
    private lateinit var swipeHelperCallback: RecyclerviewSwipeHelper

    constructor(context: Context) : super(context){
        this.todoListMemoData= TodoListMemoData(
            BaseMemoData("",DateManager.getTodayDate(), mutableListOf())
        )
        init()
    }
    constructor(context: Context,data: TodoListMemoData) : super(context,data){
        this.todoListMemoData = data
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet, data: TodoListMemoData) : super(
        context,
        attrs,
        data
    ) {
        this.todoListMemoData = data
        init()


    }


    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        binding =
            SourceMemoTodoListBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                this
            )


        adapter = TodoListMemoViewAdapter(todoListMemoData)
        binding.sourceTodoListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.sourceTodoListRecyclerView.adapter = adapter
        swipeHelperCallback =
            RecyclerviewSwipeHelper(adapter, binding.sourceTodoListRecyclerView).apply {
                // 스와이프한 뒤 고정시킬 위치 지정
                setClamp(resources.displayMetrics.widthPixels.toFloat() / 4)    // 1080 / 4 = 270
            }
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.sourceTodoListRecyclerView)
        binding.sourceTodoListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                RecyclerView.VERTICAL
            )
        )

    }

    override fun getMemoData(): BaseMemoData {
        super.getMemoData()
        return todoListMemoData
    }

    override fun setMemoData(data: BaseMemoData) {
        super.setMemoData(data)
        if(data is TodoListMemoData){
            this.todoListMemoData=data
            binding.sourceTodoListRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        swipeHelperCallback.releaseSwipedViewHolder(binding.sourceTodoListRecyclerView)
        return super.onInterceptTouchEvent(ev)
    }

}