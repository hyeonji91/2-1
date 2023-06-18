package com.example.mymemo2

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymemo2.databinding.ActivityBookMarkBinding

class BookMarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookMarkBinding
    private var dataList = arrayListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        val allEntries: Map<String, *> = sharedPreferences.all
        var i:Int =0
        for ((key, value) in allEntries) {
            val position = key.toInt()
            val content = value.toString()
            //dataList[position-1]=Data(content = content)
            dataList.add(i,Data(content = content))
            i++
        }

        //리사이클러뷰
        val menuAdapter = MenuAdapter(dataList)
        val recyclerView = binding.bookMarkRecyclerView
        recyclerView.adapter = menuAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }


