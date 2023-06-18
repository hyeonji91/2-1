package gachon.third.umc.android.Sgin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.FragmentPhoneNumberBinding
import gachon.third.umc.android.databinding.FragmentPostGridBinding


class PhoneNumberFragment : Fragment() {
    lateinit var binding: FragmentPhoneNumberBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

}