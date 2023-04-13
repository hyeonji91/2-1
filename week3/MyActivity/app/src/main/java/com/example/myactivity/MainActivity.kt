package com.example.myactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView(binding.root)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){result ->
            if(result.resultCode == RESULT_OK){
                val mString = result.data?.getStringExtra("toast")
                Toast.makeText(this@MainActivity, mString, Toast.LENGTH_SHORT).show()

            }
        }

        binding.sendBtn.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("msg", binding.sendText.text.toString())
            //startActivity(intent)
            getResultText.launch(intent)
        }




    }

}