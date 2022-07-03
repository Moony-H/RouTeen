package com.moony.routeen.ui.view.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.MovieMemoData
import com.moony.routeen.databinding.SourceMemoMovieBinding

class MovieMemoView:BaseMemoView {
    private lateinit var binding: SourceMemoMovieBinding

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, data: MovieMemoData) : super(context) {
        init()
        setMemoData(data)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, data: MovieMemoData) : super(
        context,
        attrs,
        data
    ) {
        init()
        setMemoData(data)
    }

    private fun init() {
        binding = SourceMemoMovieBinding.inflate(LayoutInflater.from(context), this)

    }

    override fun getMemoData(): BaseMemoData {


        return MovieMemoData(
            super.getMemoData(),
            binding.sourceMemoMovieDirectorName.text.toString(),
            binding.sourceMemoMovieDescriptionContent.text.toString(),
            binding.sourceMemoMovieRating.rating,
            binding.sourceMemoMovieContent.text.toString()
        )
    }

    override fun setMemoData(data: BaseMemoData) {
        super.setMemoData(data)
        if (data is MovieMemoData) {
            binding.sourceMemoMovieContent.setText(data.content)
            binding.sourceMemoMovieDescriptionContent.setText(data.description)
            binding.sourceMemoMovieDirectorName.setText(data.director)
            binding.sourceMemoMovieRating.rating = data.rating
        }

    }


}