package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.core.content.ContextCompat
import com.example.timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var started = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            start()
        }
        binding.btnPause.setOnClickListener{
            pause()
        }
        binding.btnStop.setOnClickListener{
            stop()
        }
    }

    val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            //binding.textTimer.text = formatTime(msg.arg1)
            when(msg.what){
                SET_TIME -> {
                    binding.textTimer.text = formatTime(msg.arg1)
                }
                STOP_TIME -> {
                    binding.textTimer.text = formatTime(msg.arg1)
                    binding.textTimer.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.purple_200))

                }
            }
        }
    }

    val SET_TIME = 20
    val STOP_TIME = 30

    fun start(){
        binding.textTimer.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        var stopTime=binding.editText.getText().toString().toInt()

        started = true
        //sub thread
        var total = stopTime
        binding.textTimer.text = formatTime(total)
        thread(start = true){
            while(true){
                Thread.sleep(1000)
                if(!started||total == 0) break

                total --

                val msg = Message()
                if(total == 0)
                    msg.what = STOP_TIME//명령어 저장
                else
                    msg.what = SET_TIME
                msg.arg1 = total
                handler.sendMessage(msg)

//                runOnUiThread {
//                    binding.textTimer.text = formatTime(total)
//                }
            }
        }

    }

    fun pause(){
        started = false
    }

    fun stop(){
        started = false
        binding.textTimer.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        binding.textTimer.text = "00 : 00"
    }

    fun formatTime(time:Int):String{
        val minute = String.format("%02d", time/60)
        val second = String.format("%02d", time%60)
        return "$minute : $second"
    }

}