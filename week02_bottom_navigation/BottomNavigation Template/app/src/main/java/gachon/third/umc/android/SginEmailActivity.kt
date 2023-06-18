package gachon.third.umc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.Sgin.EmailFragment
import gachon.third.umc.android.Sgin.PhoneNumberFragment
import gachon.third.umc.android.databinding.ActivitySginEmailBinding
import gachon.third.umc.android.sginapi.sginService
import gachon.third.umc.android.sginapi.sgindata
import gachon.third.umc.android.sginapi.signClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


    inner class SginEmailViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when (position){
                0-> PhoneNumberFragment()
                else -> {
                    val emailFragment = EmailFragment()
                    when{
                        intent.hasExtra("firstIntent") -> {
                            val bundle = intent.getBundleExtra("firstIntent")
                            emailFragment.arguments = bundle
                        }
                    }
                    emailFragment
                }
            }
        }


    }
}

