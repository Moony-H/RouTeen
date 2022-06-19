package com.moony.routeen.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moony.routeen.R
import com.moony.routeen.data.structure.other.CheckTextState
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.SourceItemTodoListBinding
import com.moony.routeen.databinding.SourceRecyclerviewAddButtonBinding
import java.lang.IllegalArgumentException
import java.util.*

class TodoListMemoViewAdapter(private val list: TodoListMemoData): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if(position!=list.getSize()) R.layout.source_custom_check_text_item else R.layout.source_recyclerview_add_button
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("on","create")
        return when(viewType){
            R.layout.source_custom_check_text_item->{
                val binding=DataBindingUtil.inflate<SourceItemTodoListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.source_item_todo_list,
                    parent,
                    false
                )
                CheckViewHolder(binding)
            }
            R.layout.source_recyclerview_add_button->{
                AddViewHolder(SourceRecyclerviewAddButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false)){
                    list.addTodoList(CheckTextState(false,""))
                    this.notifyItemInserted(list.getSize()-1)
                }
            }
            else-> throw IllegalArgumentException("TodoListMemoViewAdapter unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("on","bind")
        when(getItemViewType(position)){
            R.layout.source_custom_check_text_item->{
                val view=holder as CheckViewHolder

                if(view.swiped)
                    view.binding.sourceItemTodoListSwipeView.animate().x(0f).start()
                Log.d("test","bind")
                view.bind(list.todoList[position]){ viewHolder->
                    Log.d("test","list clicked")
                    Log.d("test","list ${list.todoList.size}")
                    list.removeTodoListAt(viewHolder.layoutPosition)
                    Log.d("test","list ${list.todoList.size}")
                    notifyItemRemoved(viewHolder.layoutPosition)
                }
            }
            R.layout.source_recyclerview_add_button->{
                val view=holder as AddViewHolder
                view.bind()
            }
        }
    }


    override fun getItemCount(): Int {
        return list.getSize()+1
    }

    fun swapData(fromPos: Int, toPos: Int) {
        if(toPos>=list.getSize()){
            return
        }



        list.swapTodoList(fromPos,toPos)
        notifyItemMoved(fromPos, toPos)
    }



    class CheckViewHolder(val binding:SourceItemTodoListBinding ): RecyclerView.ViewHolder(binding.root) {
        var swiped=false
        fun bind(item: CheckTextState, onClick: (RecyclerView.ViewHolder) -> Unit){
            binding.checkTextState=item
            binding.sourceItemTodoListDeleteButton.setOnClickListener {
                Log.d("Clicked","clicked")
                onClick(this)
            }
        }
    }
    class AddViewHolder(private val binding:SourceRecyclerviewAddButtonBinding,private val onClick:()->Unit):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.sourceRecyclerviewAddButton.setOnClickListener {
                onClick()
            }
        }
    }

}