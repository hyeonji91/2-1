package gachon.third.umc.android.loginapi

import com.google.gson.annotations.SerializedName

data class logindata(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("returnCode") val returnCode: Int,
    @SerializedName("returnMsg") val returnMsg: String,
    @SerializedName("result") val result: logindataResult
)

data class logindataResult(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
)
