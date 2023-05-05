package com.example.mymemo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mymemo.databinding.ActivityMainBinding

var curText : String=""
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        binding.sendBtn.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("msg", binding.sendText.text.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //if(curText.isNotEmpty()) {
            binding.sendText.setText(curText.toString())
        //}
    }
    override fun onPause() {
        super.onPause()

            curText=binding.sendText.text.toString()

        // 액티비티가 백그라운드로 들어갈 때 필요한 작업 수행

    }

    override fun onRestart() {
        super.onRestart()

        //dialog버튼
        var dlog = AlertDialog.Builder(this);
        dlog.setTitle("Qusetion");
        dlog.setMessage("이전 작업물을 지우고 다시 작성하시겠습니까?");
        dlog.setPositiveButton("Yes",  DialogInterface.OnClickListener { dialog, id ->
            binding.sendText.setText(null);
            //curText = "";
        });
        dlog.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, id->

        });
        dlog.create();
        dlog.show();
    }
}