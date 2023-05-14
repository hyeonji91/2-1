package gachon.third.umc.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StoryviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storyview)


    }
    interface OnStoryItemClickListener {
        fun onStoryItemClicked(username: String, timeAgo: String, imageResId: Int)
    }
}