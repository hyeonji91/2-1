package com.example.ch5memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch5memo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var getResultText2: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var dataList = arrayListOf<Data>()
        var menuAdapter = MenuAdapter(dataList)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.adapter = menuAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        Edit(dataList)
        menuAdapter.setItemClickListener(object : MenuAdapter.OnItemClickListener {

            override fun onClick(view: View, position: Int) {
                // 클릭 시 이벤트 작성
                val intent = Intent(this@MainActivity, AddMemoActivity::class.java)
                intent.putExtra("msg", position)
                intent.putExtra("dataList", dataList[position].content)
                getResultText2.launch(intent)

            }
        })


//        Edit(dataList)
        floatingBtn(dataList)


        dataList.add(Data("test text "))
        dataList.add(Data("test text2 "))
        dataList.add(Data("test text3 "))
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun floatingBtn(dataList: MutableList<Data>) {
        //버튼 누르면 메모 추가
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("memo")
                //받아온 데이터 사용하기
                dataList.add(Data(mString.toString()))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
        binding.floatingBtn.setOnClickListener {
            val intent = Intent(this, AddMemoActivity::class.java)
//            intent.putExtra("msg", binding.sendText.text.toString())
            getResultText.launch(intent)
        }
    }


    fun Edit(dataList: MutableList<Data>) {

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        var menuAdapter = MenuAdapter(dataList)

        getResultText2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                var mString = result.data?.getStringExtra("memo") ?: ""
                var position = result.data?.getIntExtra("position", -1) ?: -1
                //받아온 데이터 사용하기
                menuAdapter.eidtItem(position, mString)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}

