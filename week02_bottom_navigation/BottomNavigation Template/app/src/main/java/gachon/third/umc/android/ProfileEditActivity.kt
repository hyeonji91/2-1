package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import gachon.third.umc.android.databinding.ActivityProfileEditBinding
import gachon.third.umc.android.loginapi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding
    private var editId : String = ""
    private var editName : String = ""
    private var editInfo : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //profileFragment에서 데어터 받기
        if(intent.hasExtra("userId")){
            editId = intent.getStringExtra("userId").toString()
            binding.editId.setText(editId)
        }
        if(intent.hasExtra("name")){
            editName = intent.getStringExtra("name").toString()
            binding.editName.setText(editName)
        }
        if(intent.hasExtra("info")){
            editInfo = intent.getStringExtra("info").toString()
            binding.editInfo.setText(editInfo)
        }

        //profileFragment로 데어터 보내기
        binding.btnUpdateCheck.setOnClickListener{
            profileEditApi()

            val mIntent = Intent(this, ProfileFragment::class.java)
//            mIntent.putExtra("toast",binding.editId.text.toString())
//            mIntent.putExtra("toast2",binding.editName.text.toString())
//            mIntent.putExtra("toast3",binding.editInfo.text.toString())
            setResult(AppCompatActivity.RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }
        binding.btnUpdateClose.setOnClickListener{
            finish()
        }


        //사용자 이름 부분이 비어있을 때 편집 완료 체크 버튼 비활성화 되도록 설정
        binding.editId.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.editId.text.isNotEmpty())
                    binding.btnUpdateCheck.isEnabled =  true
                else
                    binding.btnUpdateCheck.isEnabled =  false
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }

        })
    }

    fun profileEditApi() {


        val token : String = App.prefs.token.toString()
        Log.d("retrofit", "token = "+token+"l");

        val requestBody = ProfileEditRequest(
            userName = binding.editId.text.toString(),
            userId= binding.editName.text.toString(),
            userIntro = binding.editInfo.text.toString(),
            userWebsite = ""
        )

        val airService = homeClient.getInstance().create(profileEditService::class.java)
        val listCall = airService.profileEdit(token, requestBody)


        listCall.enqueue(object : Callback<profileeditdata> {
            override fun onResponse(
                call: Call<profileeditdata>,
                response: Response<profileeditdata>
            ) {

                if (response.isSuccessful) {

                    Log.d("retrofit", response.body().toString());

                }else {
                    Log.e("retrofit", "onResponse: Error ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    Log.e("retrofit", "onResponse: Error Body $errorBody")
                }
            }

            override fun onFailure(call: Call<profileeditdata>, t: Throwable) {
                Log.e("retrofit", "onFailure: ${t.message}")
            }
        })
    }
}