package gachon.third.umc.android

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private var isHide = true
    private var isIdEmpty = true
    private var isPwdEmpty = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //눈 아이콘 눌렀을때 비밀번호 보이게/안보이게
        binding.btnHidepwd.setOnClickListener{
            if(isHide){
                //보이게
                isHide = false
                binding.btnHidepwd.setImageResource(R.drawable.ic_pwd_on)
                binding.pwd.transformationMethod = HideReturnsTransformationMethod.getInstance();
            } else{
                isHide = true
                binding.btnHidepwd.setImageResource(R.drawable.ic_pwd_off)
                binding.pwd.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        //id 한 자리 이상인지
        binding.id.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.id.text.isEmpty()) {
                    isIdEmpty = true
                } else {
                    isIdEmpty = false
                }
                setBtnLogin()
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })

        //pwd 한 자리 이상인지
        binding.pwd.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.pwd.text.isEmpty()) {
                    isPwdEmpty = true
                } else {
                    isPwdEmpty = false
                }
                setBtnLogin()
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })

    }

    fun setBtnLogin(){
        if(isIdEmpty||isPwdEmpty) {
            binding.btnLogin.isEnabled = false
            binding.btnLogin.setBackgroundResource(R.drawable.loginbtn_off)
        }
        else {
            binding.btnLogin.isEnabled = true
            binding.btnLogin.setBackgroundResource(R.drawable.loginbtn_on)
        }
    }
}