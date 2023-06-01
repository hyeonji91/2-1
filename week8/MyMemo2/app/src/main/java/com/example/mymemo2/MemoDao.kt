package com.example.mymemo2

import androidx.room.*
import com.example.mymemo2.Data

@Dao
interface MemoDao{
    @Query("SELECT * FROM Data")
     fun getAll(): MutableList<Data>

    @Insert
    fun insert(data: Data)
     //fun insert(data: List<Data>)

    @Update
    fun update(data: Data)
     //fun update(data: List<Data>)

    @Delete
    fun delete(data: Data)
}