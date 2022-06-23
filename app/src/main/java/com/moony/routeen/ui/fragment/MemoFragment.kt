package com.moony.routeen.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.moony.routeen.R
import com.moony.routeen.databinding.FragmentMemoBinding
import com.moony.routeen.viewmodels.MainViewModel

class MemoFragment:Fragment(),View.OnClickListener {

    private var _binding:FragmentMemoBinding?=null
    val binding:FragmentMemoBinding
        get() = _binding!!

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
        toolbar.inflateMenu(R.menu.fragment_memo_toolbar_right)

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
        when(view.id){
            R.id.frag_memo_toolbar_delete->{
                Log.d("test","delete button clicked")
            }

            R.id.frag_memo_toolbar_alarm->{
                Log.d("test","alarm button clicked")
            }

            R.id.frag_memo_test_load->{

            }
        }
    }
}