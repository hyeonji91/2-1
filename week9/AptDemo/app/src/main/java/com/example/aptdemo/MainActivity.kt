package com.example.aptdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aptdemo.cstData.FcstResponse
import com.example.aptdemo.data.AirRespoiseItem
import com.example.aptdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var airAdapter: AirAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        air()
        fcst()


    }

    fun air(){
        var aptList = listOf<AirItem>()

        val airService = RetrofitClient.getInstance().create(AirService::class.java)
        val listCall = airService.getCtprvnRltmMesureDnsty("json", "서울")

        listCall.enqueue(object : Callback<AirRespoiseItem> {
            override fun onResponse(
                call: Call<AirRespoiseItem>,
                response: Response<AirRespoiseItem>
            ) {
                if (response.isSuccessful) {
                    //Log.d("retrofit", response.body().toString());
                    Log.d("retrofit", response.body()!!.response.body.items[0].toString());
                }
            }

            override fun onFailure(call: Call<AirRespoiseItem>, t: Throwable) {
                Log.e("retrofit", Log.getStackTraceString(t));
            }
        })
    }

    fun fcst(){
        var aptList = listOf<FcstResponse>()

        val fcstService = FcstClient.getInstance().create(FcstService::class.java)
        val listCall = fcstService.getVilageFcst()

        listCall.enqueue(object : Callback<FcstResponse> {
            override fun onResponse(
                call: Call<FcstResponse>,
                response: Response<FcstResponse>
            ) {
                if (response.isSuccessful) {
                    //Log.d("retrofit", response.body().toString());
                    Log.d("retrofit", response.body()!!.response.body.items.item[0].toString());
                }
            }

            override fun onFailure(call: Call<FcstResponse>, t: Throwable) {
                Log.e("retrofit", Log.getStackTraceString(t));
            }
        })
    }
}