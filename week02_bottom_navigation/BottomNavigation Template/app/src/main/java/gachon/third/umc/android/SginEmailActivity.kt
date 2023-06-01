package gachon.third.umc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.ActivitySginEmailBinding

class SginEmailActivity : AppCompatActivity() {
    lateinit var binding: ActivitySginEmailBinding
    private val tabTitleArray = arrayOf(
        "전화번호",
        "이메일",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //Tablayout과 viewpager연결
        binding.viewPager.adapter = SginEmailViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}