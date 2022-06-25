package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.data.structure.memo.MovieMemoData
import com.moony.routeen.databinding.SourceMemoMovieBinding

class MovieMemoView:BaseMemoView {
    private lateinit var binding:SourceMemoMovieBinding
    private var movieMemoData=MovieMemoData()
    constructor(context: Context):super(context){
        init()
    }
    constructor(context: Context,data: MovieMemoData):super(context){
        this.movieMemoData=data
        init()
    }
    constructor(context: Context, attrs:AttributeSet):super(context, attrs){
        init()
    }
    constructor(context: Context, attrs:AttributeSet,data: MovieMemoData):super(context, attrs,data){
        this.movieMemoData=data
        init()
    }

    private fun init(){
        binding=SourceMemoMovieBinding.inflate(LayoutInflater.from(context),this)

        binding.sourceMemoMovieContent.setText(movieMemoData.content)
        binding.sourceMemoMovieDescriptionContent.setText(movieMemoData.description)
        binding.sourceMemoMovieDate.text=movieMemoData.date
        binding.sourceMemoMovieTitle.setText(movieMemoData.title)
        binding.sourceMemoMovieDirectorName.setText(movieMemoData.director)
        binding.sourceMemoMovieRating.rating=movieMemoData.rating

    }


}