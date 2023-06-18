package com.example.mybottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mybottomnavigation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    supportFragmentManager.beginTransaction().add(R.id.main_frame, AddFragment()).commit()
        switchNavigationBar()
    }

    fun switchNavigationBar(){
        binding.bottomNavi.run{
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_camera -> {
                        switchFragment(CameraFragment())
                    }
                    R.id.menu_add -> {
                        switchFragment(AddFragment())
                    }
                    R.id.menu_search -> {
                        switchFragment(SearchFragment())
                    }
                }
                true
            }
            //selectedItemId = R.id.menu_add
        }
    }
    fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
    }
}