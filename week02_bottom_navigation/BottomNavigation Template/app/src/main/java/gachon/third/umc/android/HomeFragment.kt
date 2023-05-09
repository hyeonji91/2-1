package gachon.third.umc.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import gachon.third.umc.android.databinding.FragmentHomeBinding
import android.graphics.Rect
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import gachon.third.umc.android.databinding.ItemPostBinding

class HomeFragment: Fragment() {

    //private lateinit var storyAdapter: StoryAdapter
    lateinit var binding: FragmentHomeBinding

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

        //스토리 리사이클러뷰
        val storyData = ArrayList<StoryData>()
        val storyAdapter = StoryAdapter(storyData)
        binding.storyRecyclerview.adapter = storyAdapter
        binding.storyRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        addStroy(storyData)
        binding.storyRecyclerview.adapter?.notifyDataSetChanged()
        binding.storyRecyclerview.addItemDecoration(RecyclerViewDecoration(32));




        //게시물 리사이클러뷰
        val postData = ArrayList<PostData>()
        val postAdapter = PostAdapter(postData)
        val postRecyclerView: RecyclerView = binding.postRecyclerview
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(activity)

        addPost(postData)

    }

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
    storyData.add(StoryData("내 스토리 ", R.drawable.ic_profile_default, R.drawable.ic_story_add, multi_type2))

    for(i in 1..9){
        storyData.add(StoryData("user$i", R.drawable.ic_profile_default, R.drawable.ic_story_border, multi_type1))
    }
//
}

fun addPost(postData: ArrayList<PostData>){
    for(i in 1..3){
        postData.add(PostData(
            R.drawable.ic_profile_default,
            "user$i",
            R.drawable.ic_launcher_background,
            SpannableString("좋아요 1개\n"+"user$i"+" 안녕하세요 가천대학교 UMC 초코$i"+"입니다\n"+"댓글 10개 모두보기\n"+"5월5일")
        ))
    }

}
