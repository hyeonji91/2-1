package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivitySginNameBinding

class SginNameActivity : AppCompatActivity() {
    lateinit var binding: ActivitySginNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySginNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sginNameBtn.setOnClickListener{
            val intent = Intent(this, SginPwdActivity::class.java)
            startActivity(intent)
        }

        nameWatcher()
    }
    fun nameWatcher(){
        //init
        binding.sginNameBtn.setBackgroundResource(R.drawable.loginbtn_off)
        binding.sginNameBtn.isEnabled = false

        binding.sginName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.sginName.text.isEmpty()) {
                    binding.sginNameBtn.setBackgroundResource(R.drawable.loginbtn_off)
                    binding.sginNameBtn.isEnabled = false
                } else {
                    binding.sginNameBtn.setBackgroundResource(R.drawable.loginbtn_on)
                    binding.sginNameBtn.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })
    }
}