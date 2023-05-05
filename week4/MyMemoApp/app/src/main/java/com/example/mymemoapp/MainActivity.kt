package com.example.mymemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymemoapp.databinding.ActivityMainBinding
import com.example.mymemoapp.databinding.RecyclerviewItemBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var binding2:RecyclerviewItemBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = RecyclerviewItemBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = ArrayList<BoardItem>()
        itemList.add(BoardItem("test text "))




        // Adapter 추가
        val menuAdapter = MenuAdapter(itemList)
        //화면생성
        menuAdapter.notifyDataSetChanged()

        // Layout manager 추가
        binding.recyclerView.adapter = menuAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


//        menuAdapter.itemClickListener = object : MenuAdapter.onItemClickListener{
//            override fun onItemClick(url: String) {
//
//                //삭제
//                var position: Int =0
//                val viewHolder = binding.recyclerView.findViewHolderForAdapterPosition(position) as MenuAdapter.menuViewHolder
//                viewHolder.memoText.text = ""
//
////                val intent = Intent(requireContext(), WebViewActivity::class.java)
////                intent.putExtra("url", url)
////                startActivity(intent)
//            }
//        }

        //버튼 누르면 메모 추가
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val mString = result.data?.getStringExtra("memo")
                binding.textView.text = mString.toString()
                itemList.add(BoardItem(mString.toString()))
                menuAdapter.setList(itemList);
            }
        }

        binding.floatingBtn.setOnClickListener{
            val intent = Intent(this, AddMemoActivity::class.java)

//            intent.putExtra("msg", binding.sendText.text.toString())
            getResultText.launch(intent)
        }

        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: BoardItem, positon : Int) {
//                Intent(this@MainActivity, AddMemoActivity::class.java).apply {
//                    putExtra("data", data.memoText)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run { startActivity(this) }
                itemList.removeAt(positon)
                menuAdapter.notifyItemRemoved(positon)
            }
            override fun onDeleteClick(v: View, data: BoardItem, positon: Int) {
                itemList.removeAt(positon)
                menuAdapter.notifyItemRemoved(positon)
            }

        })


    }
}
