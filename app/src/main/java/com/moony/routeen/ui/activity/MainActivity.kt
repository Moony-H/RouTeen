package com.moony.routeen.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moony.routeen.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity(){

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.testCheckTextView.setText("에버법베베베")
        binding.testCheckTextView.setCheckState(true)

    }
}