package com.moony.routeen.ui.activity


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.ActivityMainBinding
import com.moony.routeen.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), View.OnClickListener{

    private lateinit var binding:ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.allMemos.observe(this){


        }

    }

    override fun onClick(view: View?) {

    }
}