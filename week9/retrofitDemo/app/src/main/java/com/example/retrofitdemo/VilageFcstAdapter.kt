package com.example.retrofitdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VilageFcstAdapter : RecyclerView.Adapter<VilageFcstAdapter.MyViewHolder>() {

    private var dataList = mutableListOf<fcstItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {//실제로 표시하는 역할
        val data = dataList[position]
        holder.tv_fcstDate.text = data.fcstDate.toString()
        holder.tv_category.text = data.category.toString()
        holder.tv_fcstValue.text = data.fcstValue.toString()
        holder.tv_x.text = data.nx.toString()
        holder.tv_y.text = data.ny.toString()
    }
    override fun getItemCount(): Int = dataList.size

    fun setItem(dataList: MutableList<fcstItem>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {//선언느낌
        val tv_fcstDate: TextView = itemView.findViewById(R.id.tv_fcstDate)
        val tv_category: TextView = itemView.findViewById(R.id.tv_category)
        val tv_fcstValue: TextView = itemView.findViewById(R.id.tv_fcstValue)
        val tv_x: TextView = itemView.findViewById(R.id.tv_x)
        val tv_y: TextView = itemView.findViewById(R.id.tv_y)
    }

}