package gachon.third.umc.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import gachon.third.umc.android.Sgin.EmailFragment
import gachon.third.umc.android.Sgin.PhoneNumberFragment

//class SginEmailViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
//    override fun getItemCount(): Int {
//        return 2
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position){
//            0-> PhoneNumberFragment()
//            else -> {
//                val emailFragment = EmailFragment()
//                when{
//                    intent.hasExtra("firstIntent") -> {
//                        val bundle = intent.getBundleExtra("firstIntent")
//                        emailFragment.arguments = bundle
//                    }
//                }
//                emailFragment
//            }
//        }
//    }
//
//
//}