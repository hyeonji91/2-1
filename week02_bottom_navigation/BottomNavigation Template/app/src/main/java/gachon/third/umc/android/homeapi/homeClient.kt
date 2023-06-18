package gachon.third.umc.android.loginapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object homeClient{
    private const val BASE_URL = "https://umc.ljhhosting.com/api/"
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
