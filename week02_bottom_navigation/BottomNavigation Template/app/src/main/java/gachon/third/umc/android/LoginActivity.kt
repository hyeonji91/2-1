package gachon.third.umc.android

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.databinding.ActivityLoginBinding
import gachon.third.umc.android.loginapi.LoginRequest
import gachon.third.umc.android.loginapi.loginClient
import gachon.third.umc.android.loginapi.loginService
import gachon.third.umc.android.loginapi.logindata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private var isHide = true
    private var isIdEmpty = true
    private var isPwdEmpty = true
    //private val userId = "choco"
    //private val passwordNo = "UMC_3rd_Android"
    private var userId = "";
    private var userPwd = "";
    private var storedUserId:String? = null;
    private var storedPasswordNo:String? = null;
    private lateinit var autoLoginEdit:SharedPreferences.Editor ;

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //눈 아이콘 눌렀을때 비밀번호 보이게/안보이게
            btnHidePwd()

            //id 한 자리 이상인지
            idWatcher()
            //pwd 한 자리 이상인지
            pwdWatcher()

            binding.sign.setOnClickListener {
                //val intent = Intent(this, SginNameActivity::class.java)
                val intent = Intent(this, SginNameActivity::class.java)
                startActivity(intent)
            }


            // 자동 로그인 데이터 저장
            //주석 처리한건 apt없을 때 자동로그인 기능 ============================
            val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
             autoLoginEdit = auto.edit()
             storedUserId = auto.getString("userId", null);
             storedPasswordNo = auto.getString("passwordNo", null);

            if (storedUserId != null && storedPasswordNo != null) {
                //if (storedUserId == userId && storedPasswordNo == passwordNo) {
                    Toast.makeText(applicationContext, "자동로그인 됩니다!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                //}
            }
//            else {
//                binding.btnLogin.setOnClickListener {
//                    if (binding.id.text.toString() == userId && binding.pwd.text.toString() == passwordNo) {
//
//                        var getUserId = binding.id.text.toString()
//                        var getPwd = binding.pwd.text.toString()
//                        autoLoginEdit.putString("userId", getUserId)
//                        autoLoginEdit.putString("passwordNo", getPwd)
//                        autoLoginEdit.commit()
//                        Toast.makeText(applicationContext, "로그인 되었습니다", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    } else
//                        Toast.makeText(applicationContext, "로그인 정보가 다릅니다", Toast.LENGTH_SHORT)
//                            .show()
//
//                }
//            }
            //여기까지==================================================

            binding.btnLogin.setOnClickListener {
                loginApi()
            }


        }

    //눈 아이콘 눌렀을때 비밀번호 보이게/안보이게
    fun btnHidePwd() {
        binding.btnHidepwd.setOnClickListener {
            if (isHide) {
                //보이게
                isHide = false
                binding.btnHidepwd.setImageResource(R.drawable.ic_pwd_on)
                binding.pwd.transformationMethod = HideReturnsTransformationMethod.getInstance();
            } else {
                isHide = true
                binding.btnHidepwd.setImageResource(R.drawable.ic_pwd_off)
                binding.pwd.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }
    //id 한 자리 이상인지
    fun idWatcher(){
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
    }
    //pwd 한 자리 이상인지
    fun pwdWatcher(){
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
            binding.btnLogin.setBackgroundResource(R.drawable.loginbtn_off)
            binding.btnLogin.isEnabled = false
        }
        else {
            binding.btnLogin.setBackgroundResource(R.drawable.loginbtn_on)
            binding.btnLogin.isEnabled = true
        }
    }

    //loginApi
    fun loginApi() {


        val requestBody = LoginRequest(
            userInfo = binding.id.text.toString(),
            userPwd = binding.pwd.text.toString()
        )

        val airService = loginClient.getInstance().create(loginService::class.java)
        val listCall = airService.login(requestBody)
        Log.d("retrofit", "start call");
        listCall.enqueue(object : Callback<logindata> {
            override fun onResponse(
                call: Call<logindata>,
                response: Response<logindata>
            ) {
                Log.d("retrofit", "start response");
                if(response.isSuccessful){
                when(response.body()!!.returnCode) {
                    1000 -> {
                        Log.d("retrofit", "isSuccessful");
                        Log.d("retrofit", response.body().toString());
                        Log.d("retrofit", response.body()!!.result.jwt);
                        App.prefs.token = response.body()!!.result.jwt;

                        if (storedUserId == null && storedPasswordNo == null) {
                            autoLoginEdit.putString("userId", requestBody.userInfo)
                            autoLoginEdit.putString("passwordNo", requestBody.userPwd)
                            autoLoginEdit.commit()
                            Toast.makeText(applicationContext, "로그인 되었습니다", Toast.LENGTH_SHORT)
                                .show()
                        }

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else -> {
                        Toast.makeText(applicationContext, "${response.body()!!.returnMsg}", Toast.LENGTH_SHORT).show()
                    }
                }
                }else {
                    Log.e("retrofit", "onResponse: Error ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    Log.e("retrofit", "onResponse: Error Body $errorBody")
                }
            }

            override fun onFailure(call: Call<logindata>, t: Throwable) {
                Log.e("retrofit", "onFailure: ${t.message}")
            }
        })
    }


}