package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Previous")
    val previous: Double
)