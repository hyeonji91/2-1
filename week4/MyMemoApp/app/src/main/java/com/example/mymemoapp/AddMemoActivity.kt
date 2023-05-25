package com.example.mymemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymemoapp.databinding.ActivityAddMemoBinding
import com.example.mymemoapp.databinding.RecyclerviewItemBinding


class AddMemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if(intent.hasExtra("msg")){
//            binding.getText.text = intent.getStringExtra("msg")
//        }

        binding.saveBtn.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            mIntent.putExtra("memo",binding.writeMemo.text.toString())
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }
    }
}