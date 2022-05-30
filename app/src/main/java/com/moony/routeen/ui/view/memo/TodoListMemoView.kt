package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.SourceCustomTodoListMemoBinding
import com.moony.routeen.ui.adapter.RecyclerviewSwipeHelper
import com.moony.routeen.ui.adapter.TodoListMemoViewAdapter

class TodoListMemoView:MemoView {

    var todoListMemo=TodoListMemoData(1)
    private lateinit var binding: SourceCustomTodoListMemoBinding
    private lateinit var adapter: TodoListMemoViewAdapter
    constructor(context: Context):super(context)
    constructor(context: Context,attrs:AttributeSet):super(context, attrs)
    constructor(context: Context, attrs:AttributeSet, todoListMemoData: TodoListMemoData):super(context, attrs){
        this.todoListMemo=todoListMemoData

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        initView()
    }
    private fun initView(){
        binding=
            SourceCustomTodoListMemoBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                this
            )
        SourceCustomTodoListMemoBinding.bind(this)
        adapter= TodoListMemoViewAdapter(todoListMemo)
        binding.sourceTodoListRecyclerView.layoutManager=LinearLayoutManager(context)
        binding.sourceTodoListRecyclerView.adapter=adapter
        val swipeHelperCallback = RecyclerviewSwipeHelper(adapter).apply {
            // 스와이프한 뒤 고정시킬 위치 지정
            setClamp(resources.displayMetrics.widthPixels.toFloat() / 4)    // 1080 / 4 = 270
        }
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.sourceTodoListRecyclerView)
        binding.sourceTodoListTitleEditText.setText(todoListMemo.title)
    }

}