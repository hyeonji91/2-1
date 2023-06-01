package com.example.retrofitdemo


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface fcstApiService {
    companion object {
        private const val authKey = "0x697obEnys8rJndNVnM2teZ3IULyzBSHOvn0SDGVYKrXfZd3VdkBn9vVOZb0MNfepMZFRbdlRdOEru8zoN3Ew"
    }

    @GET("getVilageFcst?serviceKey=$authKey")
    fun getVilageFcst(
        @Query("dataType") dataType : String= "json",
        @Query("numOfRows") numOfRows : Int = 40,
        @Query("pageNo") pageNo : Int = 1,
        @Query("base_date") baseDate : Int= 20230601,
        @Query("base_time") baseTime : Int = 200,
        @Query("nx") nx : String= "62",
        @Query("ny") ny : String= "124"

        //@Query("serviceKey")
        //serviceKey: String = authKey
    ): Call<VilageFcst>
}