package gachon.third.umc.android

import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gachon.third.umc.android.databinding.ItemPostBinding
import gachon.third.umc.android.loginapi.Post


//data class PostData(
//
//    val profileData: Int,
//    val name: String,
//    val postImage: Int,
//    val context: String
//)
data class PostData(

    val profileData: String,
    val name: String,
    val postImage: String,
    val context: SpannableString
)

class PostAdapter(private val dataList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

//뷰홀더
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(ItemPostBinding.bind(view))
    }

//뷰홀더
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {//실제로 표시하는 역할

    holder.binding.id.text = dataList[position].userID

    //profile
    Glide.with(holder.binding.root)
        .load(dataList[position].userProfileImg) // 불러올 이미지 url
        .into(holder.binding.profile) // 이미지를 넣을 뷰

    //post img
    Glide.with(holder.itemView.context)
        .load(dataList[position].imgList[0].postImgUrl) // 불러올 이미지 url
        .into(holder.binding.postImage) // 이미지를 넣을 뷰


    //var s = "좋아요 1개\n"+"${dataList[position].userID}"+" ${dataList[position].postContent}"+"\n"+"댓글 10개 모두보기\n"+"${dataList[position].uploadTime}"

    val boldStyle = StyleSpan(Typeface.BOLD)
    val colorBlueSpan = ForegroundColorSpan(Color.GRAY)
    val sizeBigSpan = RelativeSizeSpan(0.8f)



//    context.setSpan(colorBlueSpan, 35, context.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//    context.setSpan(
//        sizeBigSpan,
//        context.length - 4,
//        context.length,
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//    )

    var contextHeart = SpannableString("좋아요 1개")
    contextHeart.setSpan(boldStyle, 0, contextHeart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    holder.binding.contextHeart.text = contextHeart

    var context = SpannableString("${dataList[position].userID}"+" ${dataList[position].postContent}")
    context.setSpan(boldStyle, 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    holder.binding.context.text = context

    var comment = SpannableString("댓글 10개 모두보기")
    comment.setSpan(colorBlueSpan, 0, comment.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    holder.binding.comment.text = comment

    var date = SpannableString("${dataList[position].uploadTime}")
    date.setSpan(
        sizeBigSpan,
        0,
        date.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    holder.binding.date.text = date


////spannable이용해서 글자 디자인
//    var context = SpannableString(dataList[position].context)
//
//    val boldStyle = StyleSpan(Typeface.BOLD)
//    val colorBlueSpan = ForegroundColorSpan(Color.GRAY)
//    val sizeBigSpan = RelativeSizeSpan(0.8f)
//
//    context.setSpan(boldStyle, 0 ,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//    context.setSpan(colorBlueSpan, 35 ,context.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//    context.setSpan(sizeBigSpan, context.length-4 ,context.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//    holder.binding.context.text= context
    }

    override fun getItemCount(): Int = dataList.size

//뷰홀더
    inner class PostViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){
    }


}