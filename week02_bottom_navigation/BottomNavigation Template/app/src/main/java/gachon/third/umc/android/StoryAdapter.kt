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


data class StoryData(
    val content: String,
    val profileData: Int,
    val borderData: Int
)

class StoryAdapter(private val dataList: ArrayList<StoryData>) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

//일반
//    override fun onCreateViewHolder(val binding: ItemPostBinding): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
//        return MyViewHolder(view)
//    }
//뷰홀더
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(ItemStoryBinding.bind(view))
    }

//일반
//    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {//실제로 표시하는 역할
//
//        holder.nameText.text = dataList[position].content
//        holder.image.setImageResource(dataList[position].profileData)
//        holder.border.setImageResource(dataList[position].borderData)
//    }
//뷰홀더
    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {//실제로 표시하는 역할
        holder.binding.name.text = dataList[position].content
        holder.binding.profileImg.setImageResource(dataList[position].profileData)
        holder.binding.border.setImageResource(dataList[position].borderData)
    }

    override fun getItemCount(): Int = dataList.size

//일반
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {//선언느낌
//    val nameText: TextView = itemView.findViewById(R.id.name)
//        val image: ImageView = itemView.findViewById(R.id.profileImg)
//        val border: ImageView = itemView.findViewById(R.id.border)
//    }
//뷰홀더
    inner class StoryViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){

    }

}