package com.example.myactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myactivity.databinding.ActivitySubBinding


class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        //val view = binding.root
        setContentView(binding.root)

        if(intent.hasExtra("msg")){
            binding.getText.text = intent.getStringExtra("msg")
        }
        binding.btnSwitch2.setOnClickListener{
            val intent = Intent(this, SubActivity2::class.java)
            startActivity(intent)
        }


        binding.btnSwitch1.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            mIntent.putExtra("toast","Back")
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }
    }
}