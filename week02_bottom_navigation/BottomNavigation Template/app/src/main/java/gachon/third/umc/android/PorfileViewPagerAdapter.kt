package gachon.third.umc.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import gachon.third.umc.android.Profile.InfoTagFragment
import gachon.third.umc.android.Profile.PostGridFragment

class PorfileViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> PostGridFragment()
            else -> InfoTagFragment()
        }
    }
}