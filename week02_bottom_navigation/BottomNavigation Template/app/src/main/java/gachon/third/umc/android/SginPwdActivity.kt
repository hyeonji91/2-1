package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivitySginPwdBinding

class SginPwdActivity : AppCompatActivity() {
    lateinit var binding: ActivitySginPwdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySginPwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sginPwdBtn.setOnClickListener{
            val intent = Intent(this, SginEmailActivity::class.java)
            startActivity(intent)
        }

        pwdWatcher()
    }
    fun pwdWatcher(){
        //init
        binding.sginPwdBtn.setBackgroundResource(R.drawable.loginbtn_off)
        binding.sginPwdBtn.isEnabled = false

        binding.sginPwd.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.sginPwd.text.toString().length < 6) {
                    binding.sginPwdBtn.setBackgroundResource(R.drawable.loginbtn_off)
                    binding.sginPwdBtn.isEnabled = false
                } else {
                    binding.sginPwdBtn.setBackgroundResource(R.drawable.loginbtn_on)
                    binding.sginPwdBtn.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })
    }
}