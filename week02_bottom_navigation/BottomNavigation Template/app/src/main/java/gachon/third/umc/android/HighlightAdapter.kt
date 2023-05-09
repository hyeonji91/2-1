package gachon.third.umc.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.ItemHighlightBinding
import gachon.third.umc.android.databinding.ItemNewhighlightBinding
import gachon.third.umc.android.databinding.ItemStoryBinding


data class HightlightData(
    val content: String,
    val type: Int
    //    val profileData: Int,
//    val borderData: Int,
)

class HighlightAdapter(private val dataList: ArrayList<HightlightData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when(viewType){
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_newhighlight,
                    parent,
                    false
                )
                NewHighlightViewHolder(ItemNewhighlightBinding.bind(view))
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_highlight,
                    parent,
                    false
                )
                HightlightViewHolder(ItemHighlightBinding.bind(view))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type){
            multi_type1->{
                (holder as HighlightAdapter.NewHighlightViewHolder).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            else->{
                (holder as HighlightAdapter.HightlightViewHolder).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    inner class NewHighlightViewHolder(val binding: ItemNewhighlightBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: HightlightData){
        }
    }
    inner class HightlightViewHolder(val binding:ItemHighlightBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(itme: HightlightData){

        }
    }
}