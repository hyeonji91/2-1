package gachon.third.umc.android.Sgin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import gachon.third.umc.android.LoginActivity
import gachon.third.umc.android.R
import gachon.third.umc.android.SginPwdActivity
import gachon.third.umc.android.databinding.FragmentEmailBinding


class EmailFragment : Fragment() {


    lateinit var binding: FragmentEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmailBinding.inflate(inflater, container, false)
        val view = binding.root


        emailWatcher()

//        binding.sginEmailBtn.setOnClickListener{
//            activity?.let {
//                ActivityCompat.finishAffinity(it)
//            }
//        }

        binding.btnDelEmail.setOnClickListener{
            binding.sginEmail.setText("")
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sginEmailBtn.setOnClickListener {
            activity?.let {
                ActivityCompat.finishAffinity(it)
            }
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun emailWatcher(){
        //init
        binding.sginEmailBtn.setBackgroundResource(R.drawable.loginbtn_off)
        binding.sginEmailBtn.isEnabled = false

        binding.sginEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.sginEmail.text.isEmpty()) {
                    binding.sginEmailBtn.setBackgroundResource(R.drawable.loginbtn_off)
                    binding.sginEmailBtn.isEnabled = false
                } else {
                    binding.sginEmailBtn.setBackgroundResource(R.drawable.loginbtn_on)
                    binding.sginEmailBtn.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                //if(binding.id.toString().isNotEmpty())
                //  binding.btnCheck.isEnabled =  true
            }
        })
    }
}