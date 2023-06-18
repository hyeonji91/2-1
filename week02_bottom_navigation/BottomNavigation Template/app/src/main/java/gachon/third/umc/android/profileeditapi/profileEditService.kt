package gachon.third.umc.android.loginapi


import retrofit2.Call
import retrofit2.http.*

interface profileEditService {


    @PATCH("user/mod")
    fun profileEdit(
        @Header("X-ACCESS-TOKEN")
        accessToken: String,
        @Body requestBody: ProfileEditRequest
    ) : Call<profileeditdata>
}


data class ProfileEditRequest(
    val userName: String,
    val userId: String,
    val userIntro: String,
    val userWebsite: String
)