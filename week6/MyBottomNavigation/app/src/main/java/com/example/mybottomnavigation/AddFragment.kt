package com.example.mybottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.mybottomnavigation.databinding.ActivityMainBinding
import com.example.mybottomnavigation.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    private val tabTextList = listOf("Profile", "Search", "Setting")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewpagerFragmentAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = viewpagerFragmentAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }
}