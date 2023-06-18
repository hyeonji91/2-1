package gachon.third.umc.android.sginapi


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface sginService {


    @POST("user/join")
    fun join(
        @Body requestBody: RegisterUserRequest
    ) : Call<sgindata>
}

data class RegisterUserRequest(
    val userName: String,
    val userId: String,
    val userEmail: String,
    val userPwd: String
)