package gachon.third.umc.android

import android.R
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import gachon.third.umc.android.databinding.ActivityStoryviewBinding
import kotlin.concurrent.thread


class StoryviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoryviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //데이터 받아와 스토리 화면 만들기
        var position:Int = -1
        if(intent.hasExtra("msg")){
            position = intent.getIntExtra("msg", -1)
            var userId = intent.getStringExtra("userId")
            var time = intent.getIntExtra("time", -1)
            var storyImg = intent.getIntExtra("storyImg", -1)
            if (position != -1) {
                binding.userId.text = userId
                binding.time.text = "$time"+"시간 전"
                binding.storyImg.setImageResource(storyImg)
            }
        }



        binding.seekBar.setOnTouchListener { v, event -> true }
        start()
    }





    //타이머& seekBar 제어
    val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            binding.seekBar.progress = 5000-msg.arg1
        }
    }
    var started = false
    fun start(){
        started = true
        //sub thread
        var total = 5000
        thread(start = true){
            while(true){
                Thread.sleep(1)
                if(!started||total == 0) {
                    //StoryviewActivity닫기
                    handler.post {
                        finish()
                    }
                    break
                }
                total --

                val msg = Message()
                msg.arg1 = total
                handler.sendMessage(msg)
            }
        }
    }


}