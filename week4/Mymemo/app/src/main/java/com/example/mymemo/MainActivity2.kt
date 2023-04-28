package com.example.mymemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymemo.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)

        //val view = binding.root
        setContentView(binding.root)

        if(intent.hasExtra("msg")){
            binding.textView.text = intent.getStringExtra("msg")
        }
    }
}