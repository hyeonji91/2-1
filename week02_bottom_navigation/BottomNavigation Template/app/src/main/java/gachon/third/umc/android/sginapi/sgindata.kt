package gachon.third.umc.android.sginapi

data class sgindata(
val isSuccess: Boolean,
val returnCode: Int,
val returnMsg: String,
val result: Result
)

data class Result(
    val userIdx: Int,
    val jwt: String
)
