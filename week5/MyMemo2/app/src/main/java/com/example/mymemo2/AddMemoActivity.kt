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

//        val datalist = arrayListOf<Data>()
        var position:Int = -1
        if(intent.hasExtra("msg")){
            position = intent.getIntExtra("msg", -1)
            var mString = intent.getStringExtra("dataList")
            if (position != -1) {
                val item = mString
                binding.writeMemo.setText(item)
            }


           // binding.writeMemo.setText(intent.getStringExtra("msg"))
        }



        binding.saveBtn.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            mIntent.putExtra("memo",binding.writeMemo.text.toString())
            mIntent.putExtra("position",position)
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }

    }
}