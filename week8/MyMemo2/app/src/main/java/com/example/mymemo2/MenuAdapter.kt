package com.example.mymemo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.PrimaryKey



class MenuAdapter(var dataList: MutableList<Data>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerveiw_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {//실제로 표시하는 역할
        val data = dataList[position]
        holder.contentTextView.text = data.content
        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    //클릭 이벤트 처리 ==============================================
    //리스너 인터페이스
    interface  OnItemClickListener{
        fun onClick(view: View, position: Int)

    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
    //==============================================================


    //아이템 삭제 이벤트 처리
    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataList.size)
    }
    //아이템 수정 이벤트 처리
    fun eidtItem(position: Int, editData: String) {

        dataList[position] = Data(position, editData)
        notifyItemChanged(position)
        notifyItemRangeChanged(position, dataList.size)
    }
    fun setData(newMemoList: MutableList<Data>) {
        dataList = newMemoList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {//선언느낌
        val contentTextView: TextView = itemView.findViewById(R.id.memoText)
    }

}

