package com.moony.routeen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moony.routeen.R
import com.moony.routeen.ui.view.other.CheckTextView
import com.moony.routeen.data.structure.basic.Pair
import com.moony.routeen.data.structure.memo.TodoListMemo
import com.moony.routeen.databinding.SourceRecyclerviewAddButtonBinding
import java.lang.IllegalArgumentException
import java.util.*

class TodoListMemoViewAdapter(private val list: TodoListMemo): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if(position!=list.getSize()) R.layout.source_custom_check_text_item else R.layout.source_recyclerview_add_button
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.source_custom_check_text_item->{
                CheckViewHolder(CheckTextView(parent.context))
            }
            R.layout.source_recyclerview_add_button->{
                AddViewHolder(SourceRecyclerviewAddButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false)){
                    list.addTodoList(Pair(false,""))
                    this.notifyItemInserted(list.getSize()-1)
                }
            }
            else-> throw IllegalArgumentException("TodoListMemoViewAdapter unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            R.layout.source_custom_check_text_item->{
                val view=holder as CheckViewHolder
                view.bind(list.todoList[position])
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
        Collections.swap(list.todoList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    class CheckViewHolder(private val view:CheckTextView): RecyclerView.ViewHolder(view) {
        fun bind(item:Pair<Boolean,String>){
            view.setText(item.second)
            view.setCheckState(item.first)
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