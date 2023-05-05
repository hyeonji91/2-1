package com.example.mymemo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymemo2.databinding.ActivityAddMemoBinding

class AddMemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            mIntent.putExtra("memo",binding.writeMemo.text.toString())
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }

    }
}