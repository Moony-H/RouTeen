package moony

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.databinding.SourceCustomCheckTextItemBinding

class CheckTextView:ConstraintLayout {
    private lateinit var binding: SourceCustomCheckTextItemBinding
    private lateinit var checkBox:CheckBox
    private lateinit var editText:EditText
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
                false
            )
        addView(binding.root)

        checkBox=binding.sourceCustomCheckbox
        editText=binding.sourceCustomEdit
    }

    fun getCheckState():Boolean{
        return checkBox.isChecked
    }

    fun setCheckState(isChecked:Boolean){
        checkBox.isChecked=isChecked
    }

    fun setText(text:String){
        editText.setText(text)
    }

    fun getText():String{
        return editText.text.toString()
    }
}