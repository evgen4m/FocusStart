package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Float,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Float,
    @SerializedName("Previous")
    val previous: Float
)