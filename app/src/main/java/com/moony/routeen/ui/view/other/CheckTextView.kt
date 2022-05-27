package com.moony.routeen.ui.view.other

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.data.structure.basic.Pair
import com.moony.routeen.databinding.SourceCustomCheckTextItemBinding

class CheckTextView:ConstraintLayout {
    private lateinit var binding: SourceCustomCheckTextItemBinding
    private lateinit var checkBox:CheckBox
    private lateinit var editText:EditText
    private val viewState=Pair(false,"")
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        binding =
            SourceCustomCheckTextItemBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                this,
            )
        SourceCustomCheckTextItemBinding.bind(this)
        val layoutParams=LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        this.layoutParams=layoutParams
        checkBox=binding.sourceCustomCheckbox
        editText=binding.sourceCustomEdit
        viewState.first=checkBox.isChecked
        viewState.second=editText.text.toString()
        editText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewState.second=cs.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    fun getCheckState():Boolean{
        return checkBox.isChecked
    }

    fun setCheckState(isChecked:Boolean){
        checkBox.isChecked=isChecked
        viewState.first=isChecked
    }

    fun setText(text:String){
        editText.setText(text)
        viewState.second=text
    }

    fun getText():String{
        return viewState.second
    }

    fun getCheckTextViewState():Pair<Boolean,String>{
        return viewState
    }
}