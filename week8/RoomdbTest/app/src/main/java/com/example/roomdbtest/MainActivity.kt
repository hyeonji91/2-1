package com.example.roomdbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdbtest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val db = Room.databaseBuilder(this, AppDatabase::class.java, "todo-db").allowMainThreadQueries().build()

        val db = AppDatabase.getInstance(applicationContext)!!
        CoroutineScope(Dispatchers.IO).launch {
            binding.textView.setText(db.todoDao().getAll().toString())
        }


        binding.button.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val todo1 = Todo()
                todo1.title = binding.editTextTextPersonName.text.toString()
                db.todoDao().insert(todo1)

                binding.textView.setText(db.todoDao().getAll().toString())
            }
        }
    }
}