package com.example.roomdbtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var title:String=""
)

