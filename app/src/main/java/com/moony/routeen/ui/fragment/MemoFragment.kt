package com.moony.routeen.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.moony.routeen.R
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.FragmentMemoBinding
import com.moony.routeen.ui.view.memo.BaseMemoView

import com.moony.routeen.ui.view.memo.TodoListMemoView
import com.moony.routeen.viewmodels.MainViewModel

class MemoFragment:Fragment(),View.OnClickListener,Toolbar.OnMenuItemClickListener {

    private var _binding:FragmentMemoBinding?=null
    val binding:FragmentMemoBinding
        get() = _binding!!
    private lateinit var memoView:BaseMemoView
    private val viewModel:MainViewModel by viewModels(ownerProducer ={requireActivity()})


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMemoBinding.inflate(inflater,container,false)
        val toolbar=binding.fragmentMemoToolbar
        toolbar.setNavigationIcon(R.drawable.arrow_back_48px)
        toolbar.navigationIcon
        toolbar.setNavigationOnClickListener(this)
        toolbar.inflateMenu(R.menu.menu_fragment_memo_toolbar_right)
        toolbar.setOnMenuItemClickListener(this)

        context?.let {
            memoView=TodoListMemoView(it)
            binding.fragmentMemoMemoFrame.addView(memoView)
        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(view: View) {


    }
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.frag_memo_toolbar_delete->{
                Log.d("test","delete button clicked")
            }

            R.id.frag_memo_toolbar_alarm->{
                Log.d("test","alarm button clicked")
            }

            R.id.frag_memo_test_load->{
                Log.d("test","load button clicked")

            }
            R.id.frag_memo_test_save->{
                Log.d("test","save button clicked")

            }
        }
        return true
    }

}