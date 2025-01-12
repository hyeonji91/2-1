package gachon.third.umc.android


import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.FragmentProfileBinding
import gachon.third.umc.android.loginapi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment: Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    //tablayout icon
    private val tabIconList = listOf(R.drawable.ic_postgrid, R.drawable.ic_myinfo_tag)

    override fun onResume() {
        super.onResume()
        // API 호출하여 최신 유저 정보 가져오기
        profileApi()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        profileApi()

//        getResultText = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()){ result ->
//            if(result.resultCode == AppCompatActivity.RESULT_OK){
                //profileApi()
//                val userId = result.data?.getStringExtra("toast")
//                val name = result.data?.getStringExtra("toast2")
//                val info = result.data?.getStringExtra("toast3")
//                binding.userId.text = userId
//                binding.name.text = name
//                binding.info.text = info
//
//                //만약 비어 있는 값이 있을 경우 프로필 화면에서 보이지 않도록 설정
//                if(binding.name.text.isNullOrEmpty())
//                    binding.name.visibility = View.GONE
//                if(binding.info.text.isNullOrEmpty())
//                    binding.info.visibility = View.GONE
//            }
//        }

        binding.editPro.setOnClickListener{
            val intent = Intent(requireActivity(), ProfileEditActivity::class.java)
            intent.putExtra("userId", binding.userId.text.toString())
            intent.putExtra("name", binding.name.text.toString())
            intent.putExtra("info", binding.info.text.toString())
            startActivity(intent)
            //getResultText.launch(intent)
        }



        //Tablayout과 viewpager연결
        binding.viewPager.adapter = PorfileViewPagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position->
            tab.setIcon(tabIconList[position])
        }.attach()

        //tablayout icon 색 변경 함수
        tablayoutIconColor()

        //스토리하이라이트 리사이클러뷰
        val hightlightData = ArrayList<HightlightData>()
        val hightlightAdapter = HighlightAdapter(hightlightData)
        binding.highlightRecyclerview.adapter = hightlightAdapter
        binding.highlightRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        addHighlight(hightlightData)
    }

    //tablayout icon color
    fun tablayoutIconColor(){
        val unSelected: Int = ContextCompat.getColor(requireActivity(), R.color.unselect)
        val selected: Int = ContextCompat.getColor(requireActivity(), android.R.color.black)
        //초기화
        binding.tabLayout.getTabAt(0)?.getIcon()?.setColorFilter(selected, PorterDuff.Mode.SRC_IN);
        binding.tabLayout.getTabAt(1)?.getIcon()?.setColorFilter(unSelected, PorterDuff.Mode.SRC_IN);
        //클릭했을 때
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // 선택된 탭의 아이콘 색상 변경
                tab.getIcon()?.setColorFilter(selected, PorterDuff.Mode.SRC_IN);
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // 선택이 해제된 탭의 아이콘 색상 변경
                tab.getIcon()?.setColorFilter(unSelected, PorterDuff.Mode.SRC_IN);
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing
            }
        })
    }

    fun addHighlight(hightlightData : ArrayList<HightlightData>){
        hightlightData.add(HightlightData("신규",multi_type1))
        for(i in 1..9){
            hightlightData.add(HightlightData("",multi_type2))
        }
    }

    fun profileApi() {


        val token : String = App.prefs.token.toString()
        Log.d("retrofit", "token = "+token+"l");

        val airService = homeClient.getInstance().create(profileService::class.java)
        val listCall = airService.profile(token)


        listCall.enqueue(object : Callback<profiledata> {
            override fun onResponse(
                call: Call<profiledata>,
                response: Response<profiledata>
            ) {

                if (response.isSuccessful) {
                    Log.d("retrofit", "isSuccessful :profileApi");
                    Log.d("retrofit", response.body().toString());
                    editprofile(response.body()!!.result)

                }else {
                    Log.e("retrofit", "onResponse: Error ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    Log.e("retrofit", "onResponse: Error Body $errorBody")
                }
            }

            override fun onFailure(call: Call<profiledata>, t: Throwable) {
                Log.e("retrofit", "onFailure: ${t.message}")
            }
        })
    }

    fun editprofile(result : profiledataResult){
        binding.userId.text = result.userID
        binding.name.text = result.userName
        binding.info.text = result.userIntro

        binding.postCountTv.text = "${result.postNum}"
        binding.followerCountTv.text = "${result.followerNum}"
        binding.followingCountTv.text = "${result.followingNum}"


        //만약 비어 있는 값이 있을 경우 프로필 화면에서 보이지 않도록 설정
        if(binding.name.text.isNullOrEmpty())
            binding.name.visibility = View.GONE
        if(binding.info.text.isNullOrEmpty())
            binding.info.visibility = View.GONE
    }

}