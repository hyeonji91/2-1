package com.example.myactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myactivity.databinding.ActivitySub2Binding
import com.example.myactivity.fragments.Fragment2
import com.example.myactivity.fragments.fragment_first

class SubActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySub2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySub2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment1 = fragment_first()
        val fragment2 = Fragment2()

        supportFragmentManager.beginTransaction().add(R.id.framelayout, fragment1).commit()

        binding.btn1.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.framelayout, fragment1).commit()
        }
        binding.btn2.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.framelayout, fragment2).commit()
        }


    }
}