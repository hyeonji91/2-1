package com.example.mymemo2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mymemo2.databinding.ActivityAddMemoBinding
import com.google.gson.Gson

class AddMemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddMemoBinding
    lateinit private var sharedPreferences: SharedPreferences
    var isBookMark = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMemoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)


        var realPosition:Int = -1
        var mString:String =""
        var isHeart = false
        if(intent.hasExtra("position")){
            realPosition = intent.getIntExtra("position", -1)
            mString = intent.getStringExtra("content")?:""
            isHeart = intent.getBooleanExtra("heart", false)
            if (realPosition != -1) {
                val item = mString
                binding.writeMemo.setText(item)
            }
           // binding.writeMemo.setText(intent.getStringExtra("msg"))
        }
        var data = Data(realPosition, mString)



        //화면 시작시 북마크버튼 상태 설정==========================================
        if (sharedPreferences.contains("${data.id}")){
            binding.bookMark.setImageResource(android.R.drawable.star_big_on);
            isBookMark = true;
        }else {
            binding.bookMark.setImageResource(android.R.drawable.star_big_off);
            isBookMark = false;
        }

        initheartbtn(isHeart)

        //save button
        binding.saveBtn.setOnClickListener{
            val newString = binding.writeMemo.text.toString()
            data.content = newString
            if(isBookMark){
                savePref(data)
            }
            else{
                deletePref(data)
            }
            val mIntent = Intent(this, MainActivity::class.java)
            mIntent.putExtra("memo",newString)
            mIntent.putExtra("position",realPosition)
            mIntent.putExtra("heart",isHeart)
            setResult(RESULT_OK, mIntent)
            if(!isFinishing)
                finish()
        }

        //delete button
        binding.btnDel.setOnClickListener{

            deletePref(data)

            val mIntent = Intent(this, MainActivity::class.java)
            mIntent.putExtra("delMemo",binding.writeMemo.text.toString())
            mIntent.putExtra("delPosition",realPosition)
            mIntent.putExtra("heart",isHeart)
            setResult(RESULT_OK, mIntent)
            if(!isFinishing)
                finish()
        }


        //bookmark button
        binding.bookMark.setOnClickListener{
            if (isBookMark){
                //북마크 해제
                binding.bookMark.setImageResource(android.R.drawable.star_big_off);
                //deletePref(data)
                isBookMark = false;
            }else {
                //북마크 설정
                binding.bookMark.setImageResource(android.R.drawable.star_big_on);
                //savePref(data)
                isBookMark = true;
            }
            var datalist = Data(realPosition, )
//            val bIntent = Intent(this, BookMarkActivity::class.java)
//            bIntent.putExtra("delMemo",binding.writeMemo.text.toString())
//            bIntent.putExtra("delPosition",realPosition)
//            setResult(RESULT_OK, bIntent)
        }

        //heart button
        binding.heartBtn.setOnClickListener{
            if (isHeart){
                //하트 해제
                binding.heartBtn.setImageResource(R.drawable.ic_heart)
                isHeart = false
            }else {
                //하트 설정
                binding.heartBtn.setImageResource(R.drawable.heart_on)
                isHeart = true
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            finish()
        }
    }

    //
    private fun initheartbtn(isHeart:Boolean) {
        if (isHeart){
            binding.heartBtn.setImageResource(R.drawable.heart_on)
        }else {
            binding.heartBtn.setImageResource(R.drawable.ic_heart)
        }
    }

    //sheardpreferences save
    private fun savePref(data:Data) {
        val editor = sharedPreferences.edit()
        editor.putString("${data.id}", data.content)
        editor.apply()
        Log.d("debug", "Data saved")
    }
    //sheardpreferences save
    private fun deletePref(data:Data){
        val editor = sharedPreferences.edit()
        editor.remove("${data.id}")
        // 전체 삭제는 editor.clear()
        editor.apply()
    }
}