package com.moony.routeen.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.moony.routeen.R
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.BasicMemoData
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.FragmentMemoBinding
import com.moony.routeen.ui.view.memo.BaseMemoView
import com.moony.routeen.ui.view.memo.BasicMemoView

import com.moony.routeen.ui.view.memo.TodoListMemoView
import com.moony.routeen.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MemoFragment:Fragment(),View.OnClickListener,Toolbar.OnMenuItemClickListener {

    private var _binding:FragmentMemoBinding?=null
    val binding:FragmentMemoBinding
        get() = _binding!!
    private lateinit var memoView:BaseMemoView
    private val viewModel:MainViewModel by viewModels(ownerProducer ={requireActivity()})
    private var testMemoData:BaseMemoData?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMemoBinding.inflate(inflater,container,false)
        val toolbar=binding.fragmentMemoToolbar
        toolbar.setNavigationIcon(R.drawable.arrow_back_48px)
        toolbar.setNavigationOnClickListener(this)
        toolbar.inflateMenu(R.menu.menu_fragment_memo_toolbar_right)
        toolbar.setOnMenuItemClickListener(this)

        context?.let {
            memoView= BasicMemoView(it)
            binding.fragmentMemoMemoFrame.addView(memoView)
        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


    override fun onClick(view: View) {
        when(view){

            else->{
                Log.d("MemoFragment","back press button clicked")
            }
        }

    }
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.frag_memo_toolbar_delete->{
                Log.d("test","delete button clicked")
                viewModel.deleteAllMemoData()
            }

            R.id.frag_memo_toolbar_alarm->{
                Log.d("test","alarm button clicked")
            }

            R.id.frag_memo_test_load->{
                Log.d("test","load button clicked")
                viewModel.getAllMemo(){
                    GlobalScope.launch(Dispatchers.Main) {
                        Log.d("test","it is title?? :: ${it[0].title}")
                        memoView.setMemo((it[0] as BasicMemoData))
                    }

                }

            }
            R.id.frag_memo_test_save->{
                Log.d("test","save button clicked")
                Log.d("test","save the title ${memoView.getMemo().title}")
                viewModel.insertMemo(memoView.getMemo())
            }
        }
        return true
    }

}