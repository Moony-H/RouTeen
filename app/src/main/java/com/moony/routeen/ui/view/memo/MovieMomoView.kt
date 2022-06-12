package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.databinding.SourceMemoMovieBinding

class MovieMomoView:BaseMemoView {
    private lateinit var binding:SourceMemoMovieBinding
    constructor(context: Context):super(context){
        init()
    }
    constructor(context: Context, attrs:AttributeSet):super(context, attrs){
        init()
    }

    private fun init(){
        binding=SourceMemoMovieBinding.inflate(LayoutInflater.from(context),this)

    }

}