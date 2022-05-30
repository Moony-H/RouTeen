package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.moony.routeen.data.structure.memo.TodoListMemo
import com.moony.routeen.databinding.SourceTodoListMemoBinding
import com.moony.routeen.ui.adapter.TodoListMemoViewAdapter

class TodoListMemoView:MemoView {

    var todoListMemo=TodoListMemo(1)
    private lateinit var binding:SourceTodoListMemoBinding
    private lateinit var adapter: TodoListMemoViewAdapter
    constructor(context: Context):super(context)
    constructor(context: Context,attrs:AttributeSet):super(context, attrs)
    constructor(context: Context,attrs:AttributeSet,todoListMemo: TodoListMemo):super(context, attrs){
        this.todoListMemo=todoListMemo

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        initView()
    }
    private fun initView(){
        binding=
            SourceTodoListMemoBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                this
            )
        SourceTodoListMemoBinding.bind(this)
        adapter= TodoListMemoViewAdapter(todoListMemo)
        binding.sourceTodoListRecyclerView.layoutManager=LinearLayoutManager(context)
        binding.sourceTodoListRecyclerView.adapter=adapter
        binding.sourceTodoListTitleEditText.setText(todoListMemo.title)
    }

}