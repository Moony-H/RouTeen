package com.moony.routeen.ui.view.memo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.SourceMemoTodoListBinding
import com.moony.routeen.ui.adapter.RecyclerviewSwipeHelper
import com.moony.routeen.ui.adapter.TodoListMemoViewAdapter

class TestMemo:ConstraintLayout {

    var todoListMemo = TodoListMemoData(1)
    private lateinit var binding: SourceMemoTodoListBinding
    private lateinit var adapter: TodoListMemoViewAdapter
    private lateinit var swipeHelperCallback: RecyclerviewSwipeHelper

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        binding =
            SourceMemoTodoListBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                this
            )
        //SourceCustomTodoListMemoBinding.bind(this)


        adapter = TodoListMemoViewAdapter(todoListMemo)
        binding.sourceTodoListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.sourceTodoListRecyclerView.adapter = adapter
        swipeHelperCallback =
            RecyclerviewSwipeHelper(adapter, binding.sourceTodoListRecyclerView).apply {
                // 스와이프한 뒤 고정시킬 위치 지정
                setClamp(resources.displayMetrics.widthPixels.toFloat() / 4)    // 1080 / 4 = 270
            }
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.sourceTodoListRecyclerView)
        binding.sourceTodoListTitleEditText.setText(todoListMemo.title)
        binding.sourceTodoListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                RecyclerView.VERTICAL
            )
        )

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        swipeHelperCallback.releaseSwipedViewHolder(binding.sourceTodoListRecyclerView)
        return super.onInterceptTouchEvent(ev)
    }

}