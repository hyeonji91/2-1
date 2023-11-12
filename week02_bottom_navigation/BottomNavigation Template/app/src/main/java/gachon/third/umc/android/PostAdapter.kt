package gachon.third.umc.android

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
import gachon.third.umc.android.databinding.ItemPostBinding


//data class PostData(
//
//    val profileData: Int,
//    val name: String,
//    val postImage: Int,
//    val context: String
//)
data class PostData(

    val profileData: Int,
    val name: String,
    val postImage: Int,
    val context: SpannableString
)

class PostAdapter(private val dataList: ArrayList<PostData>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

//뷰홀더
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(ItemPostBinding.bind(view))
    }

//뷰홀더
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {//실제로 표시하는 역할

        holder.binding.profile.setImageResource(dataList[position].profileData)
        holder.binding.name.text = dataList[position].name
        holder.binding.postImage.setImageResource(dataList[position].postImage)
        //holder.binding.context.text = dataList[position].context


//spannable이용해서 글자 디자인
    var context = SpannableString(dataList[position].context)

    val boldStyle = StyleSpan(Typeface.BOLD)
    val colorBlueSpan = ForegroundColorSpan(Color.GRAY)
    val sizeBigSpan = RelativeSizeSpan(0.8f)

    context.setSpan(boldStyle, 0 ,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    context.setSpan(colorBlueSpan, 35 ,context.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    context.setSpan(sizeBigSpan, context.length-4 ,context.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    holder.binding.context.text= context
    }

    override fun getItemCount(): Int = dataList.size

//뷰홀더
    inner class PostViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){

    }

}