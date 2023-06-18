package com.example.retrofitdemo

import com.google.gson.annotations.SerializedName

data class VilageFcst(
    @SerializedName("numOfRows")
    val numOfRows:String,
    @SerializedName("pageNo")
    val pageNo: String,
    @SerializedName("dataType")
    val dataType: String,
    @SerializedName("base_date")
    val baseDate: String,
    @SerializedName("base_time")
    val baseTime: String,
    @SerializedName("nx")
    val nx: String,
    @SerializedName("ny")
    val ny: String,
    @SerializedName("data")
    val data: MutableList<fcstItem>
)
data class fcstItem(
    @SerializedName("fcstDate")
    val fcstDate:String,
    @SerializedName("nx")
    val nx:String,
    @SerializedName("ny")
    val ny:String,
    @SerializedName("category")
    val category:String,
    @SerializedName("fcstValue")
    val fcstValue:String,
    @SerializedName("data")
    val data: MutableList<fcstItem>
)