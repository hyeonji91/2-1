package com.example.mymemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class BoardItem(var memoText: String)

class MenuAdapter (var itemList: ArrayList<BoardItem>):
    RecyclerView.Adapter<MenuAdapter.ViewHolder>(){


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.menuViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
//
//        return menuViewHolder(view)
//    }


    var datas = mutableListOf<BoardItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.menuViewHolder {
//    val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//    return menuViewHolder(binding)
//}

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        //holder.memoText.text = itemList[position].memoText
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    //뷰 홀더
    //inner class menuViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var memoText: TextView = itemView.findViewById(R.id.memoText)
        var root: View = itemView.findViewById(R.id.rootRecyclerview)
        fun bind(item: BoardItem){
            memoText.text = item.memoText

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    //listener?.onItemClick(itemView,item,pos)
                    listener?.onDeleteClick(itemView,item, pos)
                }
            }
        }


//        init {
//            root.setOnClickListener {
//                listener?.onDeleteClick(itemView,adapterPosition)
//            }
//        }
    }

    fun setList(list: ArrayList<BoardItem> ) {
        this.itemList = list
        notifyDataSetChanged()
    }


    //클릭 이벤트 처리
    interface OnItemClickListener{
        fun onItemClick(v:View, data: BoardItem, pos : Int)
        fun onDeleteClick(v:View, data: BoardItem, pos : Int)
    }
    private var listener:OnItemClickListener?=null

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

//    fun onDeleteClick(v: View, positon: Int) {
//        datas.removeAt(positon)
//        notifyItemRemoved(positon)
//    }

}