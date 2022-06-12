package com.moony.routeen.ui.view.other

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.R
import com.moony.routeen.data.structure.other.CheckTextState
import com.moony.routeen.databinding.SourceCustomCheckTextItemBinding

class CheckTextView:ConstraintLayout {
    private lateinit var binding: SourceCustomCheckTextItemBinding
    private lateinit var checkBox:CheckBox
    private lateinit var editText:EditText


    private val viewState= CheckTextState(false,"")
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {


        initView()
        val typedArray=context.obtainStyledAttributes(attrs, R.styleable.CheckTextView)
        setChecked(typedArray.getBoolean(R.styleable.CheckTextView_checked,false))
        typedArray.getString(R.styleable.CheckTextView_text)?.let { setText(it) }
        typedArray.recycle()
    }

    private fun initView() {
        binding =
            SourceCustomCheckTextItemBinding.inflate(
                LayoutInflater.from(context),
                this
            )

        val layoutParams=LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        this.layoutParams=layoutParams
        checkBox=binding.sourceCustomCheckbox
        editText=binding.sourceCustomEdit
        viewState.checked=checkBox.isChecked
        viewState.text=editText.text.toString()
        editText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewState.text=cs.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}

        })

        checkBox.setOnClickListener {
            viewState.checked=checkBox.isChecked
        }
    }



    fun setChecked(isChecked:Boolean){
        checkBox.isChecked=isChecked
        viewState.checked=isChecked
    }

    fun setText(text:String){
        editText.setText(text)
        viewState.text=text
    }

    fun getText():String{
        return viewState.text
    }

    fun getCheckTextViewState(): CheckTextState {
        return viewState
    }


}