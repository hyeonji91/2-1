package com.example.aptdemo

import com.example.aptdemo.cstData.FcstResponse
import com.example.aptdemo.data.AirRespoiseItem
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AirService {
    companion object {
        private const val authKey = "0x697obEnys8rJndNVnM2teZ3IULyzBSHOvn0SDGVYKrXfZd3VdkBn9vVOZb0MNfepMZFRbdlRdOEru8zoN3Ew%3D%3D"
    }

    @GET("getCtprvnRltmMesureDnsty?serviceKey=$authKey")
    fun getCtprvnRltmMesureDnsty(
        @Query("returnType") //계약 년도
        returnType: String = "json",
        @Query("sidoName") //매매가
        sidoName: String = "서울",
    ) : Call<AirRespoiseItem>
}

interface FcstService {
    companion object {
        private const val authKey = "0x697obEnys8rJndNVnM2teZ3IULyzBSHOvn0SDGVYKrXfZd3VdkBn9vVOZb0MNfepMZFRbdlRdOEru8zoN3Ew%3D%3D"
    }

    @GET("getVilageFcst?serviceKey=$authKey")
    fun getVilageFcst(
        @Query("dataType")
        dataType: String = "json",
        @Query("pageNo")
        returnType: String = "1" ,
        @Query("base_date")
        base_date: String ="20230602", //오늘날짜
        @Query("base_time")
        base_time: String = "0500",
        @Query("nx")
        nx: String = "55",
        @Query("ny")
        ny: String = "127",
    ) : Call<FcstResponse>
}