package com.example.mymemo2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemoEntity (
    val memoText : String
        )  {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}