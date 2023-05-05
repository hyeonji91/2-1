package com.example.mymemo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Data(
    val content: String
)

class MenuAdapter(private val dataList: List<Data>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerveiw_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {//실제로 표시하는 역할
        val data = dataList[position]
        holder.contentTextView.text = data.content
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {//선언느낌
        val contentTextView: TextView = itemView.findViewById(R.id.memoText)
    }

}
