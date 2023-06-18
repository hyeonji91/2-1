package gachon.third.umc.android

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.Sgin.EmailFragment
import gachon.third.umc.android.databinding.ActivitySginuserNameBinding

class SginuserNameActivity : AppCompatActivity() {
    lateinit var binding: ActivitySginuserNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySginuserNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usernameWatcher()

        var userId : String = "";
        if(intent.hasExtra("userId")){
            userId = intent.getStringExtra("userId").toString()
        }
        var userPwd : String = "";
        if(intent.hasExtra("userPwd")){
            userPwd = intent.getStringExtra("userPwd").toString()
        }

        binding.sginUserNameBtn.setOnClickListener{
            val intent = Intent(this, SginEmailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("userId", userId)
            bundle.putString("userPwd",userPwd);
            bundle.putString("userName",binding.sginUserName.text.toString())

            intent.putExtra("firstIntent", bundle)

            startActivity(intent)
        }



    }

    fun usernameWatcher(){
        //init
        binding.sginUserNameBtn.setBackgroundResource(R.drawable.loginbtn_off)
        binding.sginUserNameBtn.isEnabled = false

        binding.sginUserName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.sginUserName.text.isEmpty()) {
                    binding.sginUserNameBtn.setBackgroundResource(R.drawable.loginbtn_off)
                    binding.sginUserNameBtn.isEnabled = false
                } else {
                    binding.sginUserNameBtn.setBackgroundResource(R.drawable.loginbtn_on)
                    binding.sginUserNameBtn.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })
    }
}