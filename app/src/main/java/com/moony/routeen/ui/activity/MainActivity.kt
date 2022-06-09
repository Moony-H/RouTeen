package com.moony.routeen.ui.activity


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.databinding.ActivityMainBinding
import com.moony.routeen.ui.view.other.ImageControlView
import com.moony.routeen.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), View.OnClickListener{

    private lateinit var binding:ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val imageControlView=ImageControlView(baseContext)
        //val layoutParams=ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        imageControlView.id=View.generateViewId()

        binding.activityMainParent.addView(imageControlView)
        val textView= EditText(baseContext)
        binding.activityMainParent.addView(textView)
        val temp=binding.activityMainParent.getChildAt(0)
        if(temp is ImageControlView){
            Log.d("test","done....")
        }
        binding.activityMainParent.invalidate()
        val constraintSet= ConstraintSet()
        //constraintSet.clone(binding.activityMainParent)
//
        //constraintSet.connect(imageControlView.id, ConstraintSet.TOP, binding.activityMainParent.id, ConstraintSet.TOP, )
        //constraintSet.connect(imageControlView.id, ConstraintSet.BOTTOM, binding.activityMainParent.id, ConstraintSet.BOTTOM, )
        //constraintSet.connect(imageControlView.id, ConstraintSet.LEFT, binding.activityMainParent.id, ConstraintSet.LEFT, )
        //constraintSet.connect(imageControlView.id, ConstraintSet.RIGHT, binding.activityMainParent.id, ConstraintSet.RIGHT, )
//
        //constraintSet.applyTo(binding.activityMainParent)


        //val temp=binding.activityMainParent.getAllImageControlView()
        //Log.d("test","size ${temp.size}")


    }

    override fun onClick(view: View?) {

    }
}