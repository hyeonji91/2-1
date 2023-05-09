package gachon.third.umc.android

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.ItemNewstoryBinding
import gachon.third.umc.android.databinding.ItemPostBinding
import gachon.third.umc.android.databinding.ItemStoryBinding

const val multi_type1 = 1 //item_story
const val multi_type2 = 2 //itme_newstroy
data class StoryData(
    val content: String,
    val profileData: Int,
    val borderData: Int,
    val type: Int
)

class StoryAdapter(private val dataList: ArrayList<StoryData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }
//일반
//    override fun onCreateViewHolder(val binding: ItemPostBinding): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
//        return MyViewHolder(view)
//    }
//뷰홀더
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when(viewType){
            multi_type1-> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_story,
                    parent,
                    false)
                StoryViewHolder1(ItemStoryBinding.bind(view))
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_newstory,
                    parent,
                    false)
                StoryViewHolder2(ItemNewstoryBinding.bind(view))
            }
        }
    }

//일반
//    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {//실제로 표시하는 역할
//
//        holder.nameText.text = dataList[position].content
//        holder.image.setImageResource(dataList[position].profileData)
//        holder.border.setImageResource(dataList[position].borderData)
//    }
//뷰홀더
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {//실제로 표시하는 역할
        when(dataList[position].type){
            multi_type1->{
                (holder as StoryViewHolder1).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            else->{
                (holder as StoryViewHolder2).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
        }

    }

    override fun getItemCount(): Int = dataList.size

//일반
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {//선언느낌
//    val nameText: TextView = itemView.findViewById(R.id.name)
//        val image: ImageView = itemView.findViewById(R.id.profileImg)
//        val border: ImageView = itemView.findViewById(R.id.border)
//    }
//뷰홀더
    inner class StoryViewHolder1(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: StoryData) {
            binding.name.text = item.content
            binding.profileImg.setImageResource(item.profileData)
            binding.border.setImageResource(item.borderData)

        }
    }
    inner class StoryViewHolder2(val binding: ItemNewstoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: StoryData) {
            binding.name.text = item.content
            binding.profileImg.setImageResource(item.profileData)
            binding.border.setImageResource(item.borderData)

        }
    }

}