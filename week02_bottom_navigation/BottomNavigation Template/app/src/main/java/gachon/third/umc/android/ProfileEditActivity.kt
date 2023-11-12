package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //profileFragment에서 데어터 받기
        if(intent.hasExtra("userId")){
            binding.editId.setText(intent.getStringExtra("userId"))
        }
        if(intent.hasExtra("name")){
            binding.editName.setText(intent.getStringExtra("name"))
        }
        if(intent.hasExtra("info")){
            binding.editInfo.setText(intent.getStringExtra("info"))
        }

        //profileFragment로 데어터 보내기
        binding.btnUpdateCheck.setOnClickListener{
            val mIntent = Intent(this, ProfileFragment::class.java)
            mIntent.putExtra("toast",binding.editId.text.toString())
            mIntent.putExtra("toast2",binding.editName.text.toString())
            mIntent.putExtra("toast3",binding.editInfo.text.toString())
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
}