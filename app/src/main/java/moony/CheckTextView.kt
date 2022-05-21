package moony

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.moony.routeen.databinding.SourceCustomCheckTextItemBinding

class CheckTextView:ConstraintLayout {
    private lateinit var binding: SourceCustomCheckTextItemBinding

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
    }
}