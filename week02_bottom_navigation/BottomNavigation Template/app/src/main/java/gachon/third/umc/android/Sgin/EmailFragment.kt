package gachon.third.umc.android.Sgin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import gachon.third.umc.android.LoginActivity
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.FragmentEmailBinding
import gachon.third.umc.android.sginapi.RegisterUserRequest
import gachon.third.umc.android.sginapi.sginService
import gachon.third.umc.android.sginapi.sgindata
import gachon.third.umc.android.sginapi.signClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmailFragment : Fragment() {


    lateinit var binding: FragmentEmailBinding
    private var userId : String = "";
    private var userPwd : String = "";
    private var userName : String = "";
    private var userEmail: String = "";
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmailBinding.inflate(inflater, container, false)
        val view = binding.root


        val keySet = this.arguments?.keySet()
        if (keySet != null) {
            userId = this.arguments?.getString("userId").toString()
            userPwd = this.arguments?.getString("userPwd").toString()
            userName = this.arguments?.getString("userName").toString()
            Log.d("this.arguments?.", "$userName, $userId, $userEmail, $userPwd");
            }


        emailWatcher()

        binding.btnDelEmail.setOnClickListener{
            binding.sginEmail.setText("")
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sginEmailBtn.setOnClickListener {
            sgin(userId, userPwd, userName)
            activity?.let {
                ActivityCompat.finishAffinity(it)
            }
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun emailWatcher(){
        //init
        binding.sginEmailBtn.setBackgroundResource(R.drawable.loginbtn_off)
        binding.sginEmailBtn.isEnabled = false

        binding.sginEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.sginEmail.text.isEmpty()) {
                    binding.sginEmailBtn.setBackgroundResource(R.drawable.loginbtn_off)
                    binding.sginEmailBtn.isEnabled = false
                } else {
                    binding.sginEmailBtn.setBackgroundResource(R.drawable.loginbtn_on)
                    binding.sginEmailBtn.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })
    }

    fun sgin(userId:String, userPwd:String, userName:String) {
        Log.d("retrofit", "start sgin");
        userEmail = binding.sginEmail.text.toString();
        val requestBody = RegisterUserRequest(
            userName = userName,
            userId = userId,
            userEmail = userEmail,
            userPwd = userPwd
        )

        val airService = signClient.getInstance().create(sginService::class.java)
        val listCall = airService.join(requestBody)
        Log.d("retrofit", "start call");
        listCall.enqueue(object : Callback<sgindata> {
            override fun onResponse(
                call: Call<sgindata>,
                response: Response<sgindata>
            ) {
                Log.d("retrofit", "start response");
                Log.d("retrofit", "$userName, $userId, $userEmail, $userPwd");
                if (response.isSuccessful) {
                    Log.d("retrofit", "isSuccessful");
                    Log.d("retrofit", response.body().toString());
                }else {
                    Log.e("retrofit", "onResponse: Error ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    Log.e("retrofit", "onResponse: Error Body $errorBody")
                }
            }

            override fun onFailure(call: Call<sgindata>, t: Throwable) {
                Log.e("retrofit", "onFailure: ${t.message}")
            }
        })
    }
}