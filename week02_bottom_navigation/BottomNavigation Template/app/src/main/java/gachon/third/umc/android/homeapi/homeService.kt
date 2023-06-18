package gachon.third.umc.android.loginapi


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface homeService {


    @GET("post/home")
    fun home(
        @Header("X-ACCESS-TOKEN")
        accessToken: String
    ) : Call<homedata>
}

//@Body requestBody: LoginRequest
//data class LoginRequest(
//    @SerializedName("userInfo") val userInfo: String,
//    @SerializedName("userPwd") val userPwd: String
//)