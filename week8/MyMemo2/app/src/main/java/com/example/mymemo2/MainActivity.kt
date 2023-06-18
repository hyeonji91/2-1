package com.example.mymemo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymemo2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var getResultText2: ActivityResultLauncher<Intent>
    //리사이클러뷰
    var dataList = arrayListOf<Data>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    //Roomdb
    lateinit var db: MemoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //리사이클러뷰
        menuAdapter = MenuAdapter(dataList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = menuAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        var loadData: List<Data>? = null
        //Roomdb에서 데이터 불러오기
        db = MemoDatabase.getInstance(applicationContext)!!
        loadData = db.memoDao().getAll()
        dataList = ArrayList(loadData)
        menuAdapter.setData(dataList)



        //item누르면 메모 수정
        Edit(dataList)
        //item누르면 메모수정 화면으로 데이터 전달
        menuAdapter.setItemClickListener(object : MenuAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                // 클릭 시 이벤트 작성
                val intent = Intent(this@MainActivity, AddMemoActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("content", dataList[position].content)
                intent.putExtra("heart", dataList[position].memoHeart)
                getResultText2.launch(intent)

            }
        })

        //버튼 누르면 메모 추가
        floatingBtn(dataList)

        goToBookMarkActivity()


    }


    //북마크 액티비티로 화면 전환
    fun goToBookMarkActivity(){
        binding.bookMarkFloatingButton.setOnClickListener{
            val intent = Intent(this, BookMarkActivity::class.java)
            startActivity(intent)
        }
    }

    //버튼 누르면 메모 추가
    fun floatingBtn(dataList: MutableList<Data>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data!!.getStringExtra("memo")?:""
                val position = result.data!!.getIntExtra("position",-1)
                val heart = result.data!!.getBooleanExtra("heart", false)
                //받아온 데이터 사용하기

                //Roomdb 데이터 저장하기
                var memodata = Data()
                memodata.content = mString
                memodata.memoHeart = heart
                db.memoDao().insert(memodata)
                //리사이클러뷰에 반영
                dataList.add(memodata)
                recyclerView.adapter?.notifyItemInserted(dataList.size - 1)
            }
        }
        binding.floatingBtn.setOnClickListener {
            val intent = Intent(this, AddMemoActivity::class.java)
            getResultText.launch(intent)
        }
    }

    //item누르면 수정
    fun Edit(dataList: MutableList<Data>) {

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        var menuAdapter = MenuAdapter(dataList)

        getResultText2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                //save버튼
                if(result.data?.hasExtra("memo") == true) {
                    var mString = result.data?.getStringExtra("memo") ?: ""
                    var position = result.data?.getIntExtra("position", -1) ?: -1
                    val heart = result.data!!.getBooleanExtra("heart", false)
                    //받아온 데이터 사용하기

                    //Roomdb에서 데이터 업데이트
                    val item = dataList[position]
                    item.content = mString
                    item.memoHeart = heart
                    Log.d("id", "updata = ${item.id}")
                    db.memoDao().update(item)
                    recyclerView.adapter?.notifyItemChanged(position)

                }
                else{//delete버튼
                    var delString = result.data?.getStringExtra("delMemo") ?: ""
                    var delposition = result.data?.getIntExtra("delPosition", -1) ?: -1
                    val heart = result.data!!.getBooleanExtra("heart", false)

                    //받아온 데이터 사용하기
                    val item = dataList[delposition]
                    Log.d("id", "delete = ${item.id}")
                    db.memoDao().delete(item)
                    //리사이클러뷰에 반영
                    menuAdapter.removeItem(delposition)
                    recyclerView.adapter?.notifyDataSetChanged()
                }


            }
        }
    }


}

