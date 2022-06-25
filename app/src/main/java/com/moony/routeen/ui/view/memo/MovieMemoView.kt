package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.data.structure.memo.BaseMemoData
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
        binding.sourceMemoMovieDirectorName.setText(movieMemoData.director)
        binding.sourceMemoMovieRating.rating=movieMemoData.rating

    }

    override fun getMemoData(): BaseMemoData {
        super.getMemoData()
        this.movieMemoData.director=binding.sourceMemoMovieDirectorName.text.toString()
        this.movieMemoData.content=binding.sourceMemoMovieContent.text.toString()
        this.movieMemoData.description=binding.sourceMemoMovieDescriptionContent.text.toString()
        this.movieMemoData.rating=binding.sourceMemoMovieRating.rating
        return this.movieMemoData
    }


}