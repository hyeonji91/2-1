package gachon.third.umc.android.loginapi


import retrofit2.Call
import retrofit2.http.*

interface profileService {


    @GET("user/profile")
    fun profile(
        @Header("X-ACCESS-TOKEN")
        accessToken: String
    ) : Call<profiledata>
}

//@Body requestBody: LoginRequest
//data class LoginRequest(
//    @SerializedName("userInfo") val userInfo: String,
//    @SerializedName("userPwd") val userPwd: String
//)