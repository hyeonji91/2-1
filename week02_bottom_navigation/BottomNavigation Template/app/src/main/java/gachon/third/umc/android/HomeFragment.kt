package gachon.third.umc.android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import gachon.third.umc.android.databinding.FragmentHomeBinding
import android.graphics.Rect
import android.util.Log
import gachon.third.umc.android.loginapi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment: Fragment() {

    //private lateinit var storyAdapter: StoryAdapter
    val postData = ArrayList<Post>()
    lateinit var binding: FragmentHomeBinding
    //private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeApi()

        //스토리 리사이클러뷰
        val storyData = ArrayList<StoryData>()
        val storyAdapter = StoryAdapter(storyData)

        binding.storyRecyclerview.adapter = storyAdapter
        binding.storyRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        addStroy(storyData)
        binding.storyRecyclerview.adapter?.notifyDataSetChanged()
        //리사이클러뷰 아이템 간 간격 조절
        binding.storyRecyclerview.addItemDecoration(RecyclerViewDecoration(32));




        //게시물 리사이클러뷰
        //val postData = ArrayList<PostData>()
        val postAdapter = PostAdapter(postData)

        val postRecyclerView: RecyclerView = binding.postRecyclerview
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(activity)

        //addPost(postData)


        //StoryviewActivity로 스토리 화면 만드는 데이터 보내기
        storyAdapter.setItemClickListener(object : StoryAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                //스토리 클릭 시 이벤트 작성
                val intent = Intent(requireActivity(), StoryviewActivity::class.java)
                intent.putExtra("msg", position)
                intent.putExtra("userId", storyData[position].userId)
                intent.putExtra("time", storyData[position].time)
                intent.putExtra("storyImg", storyData[position].storyImg)
                startActivity(intent)

//                getResultText.launch(intent)
            }
        })
    }


    class RecyclerViewDecoration(private val divWidth: Int) : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.right = divWidth
        }
    }

    fun addStroy(storyData : ArrayList<StoryData>){



    storyData.add(StoryData("내 스토리 ", R.drawable.ic_profile_default, R.drawable.ic_story_add,1,R.drawable.story3, multi_type2))
    val storyImg = arrayListOf(R.drawable.story1, R.drawable.story2, R.drawable.story3)
    for(i in 1..9){
        storyData.add(StoryData("user$i", R.drawable.ic_profile_default, R.drawable.ic_story_border, i, storyImg[i%3] , multi_type1))

    }

    }

    fun addPost(postData: ArrayList<Post>){
    //fun addPost(homedata: homedata){

        val postAdapter = PostAdapter(postData)

        val postRecyclerView: RecyclerView = binding.postRecyclerview
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(
           requireContext(),LinearLayoutManager.VERTICAL, false)

        //homedata.result[i].
//        for(i in 1..4){
//            Log.d("retrofit", "${homedata.result[i].userProfileImg} , ${homedata.result[i].userID}, ${homedata.result[i].imgList[0].postImgUrl}");
//            postData.add(PostData(
//                homedata.result[i].userProfileImg,
//                homedata.result[i].userID,
//                homedata.result[i].imgList[0].postImgUrl,
//                SpannableString("좋아요 1개\n"+"user$i"+" 안녕하세요 가천대학교 UMC 초코$i"+"입니다\n"+"댓글 10개 모두보기\n"+"5월5일")
//            ))
//        }

    }

    fun homeApi() {


        val token : String = App.prefs.token.toString()
        Log.d("retrofit", "token = "+token+"l");

        val airService = homeClient.getInstance().create(homeService::class.java)
        val listCall = airService.home(token)

        Log.d("retrofit", "start call");
        listCall.enqueue(object : Callback<homedata> {
            override fun onResponse(
                call: Call<homedata>,
                response: Response<homedata>
            ) {
                Log.d("retrofit", "start response");
                if (response.isSuccessful) {
                    Log.d("retrofit", "isSuccessful");
                    Log.d("retrofit", response.body().toString());
                    addPost(response.body()!!.result)

                }else {
                    Log.e("retrofit", "onResponse: Error ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    Log.e("retrofit", "onResponse: Error Body $errorBody")
                }
            }

            override fun onFailure(call: Call<homedata>, t: Throwable) {
                Log.e("retrofit", "onFailure: ${t.message}")
            }
        })
    }

}
