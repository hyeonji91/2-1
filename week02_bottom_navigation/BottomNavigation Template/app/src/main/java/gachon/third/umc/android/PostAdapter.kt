package gachon.third.umc.android

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.ItemPostBinding
import gachon.third.umc.android.databinding.ItemStoryBinding


data class PostData(
    val content: String,
    val profileData: Int,
    val borderData: Int
)

class PostAdapter(private val dataList: ArrayList<PostData>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

//뷰홀더
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(ItemStoryBinding.bind(view))
    }

//뷰홀더
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {//실제로 표시하는 역할
        holder.binding.name.text = dataList[position].content
        holder.binding.profileImg.setImageResource(dataList[position].profileData)
        holder.binding.border.setImageResource(dataList[position].borderData)
    }

    override fun getItemCount(): Int = dataList.size

//뷰홀더
    inner class PostViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){

    }

}