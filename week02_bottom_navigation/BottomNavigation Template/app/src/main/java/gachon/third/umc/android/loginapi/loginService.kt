package gachon.third.umc.android.loginapi


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface loginService {


    @POST("user/login")
    fun login(
        @Body requestBody: LoginRequest
    ) : Call<logindata>
}

data class LoginRequest(
    @SerializedName("userInfo") val userInfo: String,
    @SerializedName("userPwd") val userPwd: String
)