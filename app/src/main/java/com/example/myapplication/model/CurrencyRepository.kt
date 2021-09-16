package com.example.myapplication.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrencyRepository(private val api: CurrencyApi) {

    fun getAll(callback: (List<CurrencyModel>) -> Unit) {
        var callbackList = ArrayList<CurrencyModel>()
        val call = api.getAllPosts()
        call!!.enqueue(object : Callback<CurrencyModel> {
            override fun onResponse(
                call: Call<CurrencyModel>,
                response: Response<CurrencyModel>
            ) {
                val result = response.body()
                if(response.isSuccessful) {
                    print(result)
                    callback(callbackList)
                }
            }

            override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

}