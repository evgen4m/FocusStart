package com.example.myapplication.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/daily_json.js")
    fun getAllPosts(): Call<CurrencyResponse>?

}