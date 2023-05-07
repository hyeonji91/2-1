package gachon.third.umc.android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import gachon.third.umc.android.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == AppCompatActivity.RESULT_OK){
                val userId = result.data?.getStringExtra("toast")
                val name = result.data?.getStringExtra("toast2")
                val info = result.data?.getStringExtra("toast3")
                binding.userId.text = userId
                binding.name.text = name
                binding.info.text = info

                //만약 비어 있는 값이 있을 경우 프로필 화면에서 보이지 않도록 설정
                if(binding.name.text.isNullOrEmpty())
                    binding.name.visibility = View.GONE
                if(binding.info.text.isNullOrEmpty())
                    binding.info.visibility = View.GONE
            }
        }
        binding.editPro.setOnClickListener{


            val intent = Intent(requireActivity(), ProfileEditActivity::class.java)
            intent.putExtra("userId", binding.userId.text.toString())
            intent.putExtra("name", binding.name.text.toString())
            intent.putExtra("info", binding.info.text.toString())
            //startActivity(intent)
            getResultText.launch(intent)
        }
    }

}