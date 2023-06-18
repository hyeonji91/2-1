package gachon.third.umc.android.loginapi

data class homedata(
    val isSuccess: Boolean,
    val returnCode: Int,
    val returnMsg: String,
    val result: ArrayList<Post>
)

data class Post(
    val postIdx: Int,
    val userID: String,
    val userProfileImg: String,
    val postContent: String,
    val commentNum: Int,
    val uploadTime: String,
    val imgList: List<PostImage>,
    val commentList: List<Comment>
)

data class PostImage(
    val postImgIdx: Int,
    val postImgUrl: String
)

data class Comment(
    val commentIdx: Int,
    val commentContents: String,
    val userID: String
)
