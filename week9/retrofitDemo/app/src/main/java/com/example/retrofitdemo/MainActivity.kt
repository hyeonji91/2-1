package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var vilageFcstAdapter: VilageFcstAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vilageFcstAdapter = VilageFcstAdapter()
        binding.rvList.apply {
            adapter = vilageFcstAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        binding.btnSwitch.setOnClickListener{
            requestVilageFcst()

        }


//        //웹브라우저 창 열기
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://prodmo.eric-rc.shop/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        //주소 입력
//        val apiService = retrofit.create(APIService::class.java)
//
//        //입력한 주소 중에 하나로 연결 시도: 요청 보내기
//        apiService.getCheckEmail("abc@abc.com").enqueue(object: Callback<Response>{
//            //응답 받으면
//            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//                if(response.isSuccessful) {
//                    val responseData = response.body()
//
//                    if(responseData!=null){
//                        Log.d("Retrofit", "Response\nCode: ${responseData.code} Message: ${responseData.message}")
//                    }
//                    else{
//                        Log.w("Retrofit", "Response Not Successful ${response.code()}")
//                    }
//
//                }
//
//                }
//            //서버와 연결 실패시
//            override fun onFailure(call: Call<Response>, t: Throwable) {
//                Log.e("Retrofit", "Error!", t)
//            }
//
//        })
    }

    private fun requestVilageFcst(){
        val fcstService = RetrofitClient.getInstance().create(fcstApiService::class.java)
        val listCall = fcstService.getVilageFcst()
        listCall.enqueue(object: Callback<VilageFcst>{
            override fun onResponse(
                call: Call<VilageFcst>,
                response: retrofit2.Response<VilageFcst>
            ) {
                if(response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("Retrofit", "Response success")
                    Log.d("Retrofit", "Response\nCode: ${responseData.toString()}")
                    binding.tvFcstDate.text = response.body()?.data.toString()
                    vilageFcstAdapter?.let {
                        it.setItem(response.body()?.data ?: mutableListOf())
                    }
                }
            }

            override fun onFailure(call: Call<VilageFcst>, t: Throwable) {
                // 실패
                Log.e("main_error", t.stackTrace.toString());
            }

        })



    }
}