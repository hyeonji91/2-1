package gachon.third.umc.android.loginapi

data class profiledata(
    val isSuccess: Boolean,
    val returnCode: Int,
    val returnMsg: String,
    val result: profiledataResult
)

data class profiledataResult(
    val userIdx: Int,
    val userName: String,
    val userIntro: String,
    val userWebsite: String,
    val userProfileImg: String,
    val postNum: Int,
    val followerNum: Int,
    val followingNum: Int,
    val userID: String
)
