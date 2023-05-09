package gachon.third.umc.android.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.FragmentInfoTagBinding


class InfoTagFragment : Fragment() {
    lateinit var binding: FragmentInfoTagBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoTagBinding.inflate(inflater, container, false)

        return binding.root
    }


}