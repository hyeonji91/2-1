package com.example.mymemo2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var content: String="",
    var memoHeart: Boolean=false
){
    //@PrimaryKey(autoGenerate = true) var id:Int = 0
}

