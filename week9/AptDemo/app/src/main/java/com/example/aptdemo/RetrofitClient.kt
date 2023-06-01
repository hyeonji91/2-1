package com.example.aptdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/"
    private var instance: Retrofit? = null

    open fun getInstance() : Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        } // end if

        return instance!!
    }
}

object FcstClient {
    private const val BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"
    private var instance: Retrofit? = null

    open fun getInstance() : Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        } // end if

        return instance!!
    }
}