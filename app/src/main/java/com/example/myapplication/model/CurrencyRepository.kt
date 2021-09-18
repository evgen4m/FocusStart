package com.example.myapplication.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrencyRepository(private val api: CurrencyApi) {

    fun getAll(callback: (List<CurrencyModel>) -> Unit) {
        var callbackList = ArrayList<CurrencyModel>()
        val call = api.getAllPosts()
        call!!.enqueue(object : Callback<CurrencyResponse> {
            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                val currencyResponse = response.body()
                for (value in currencyResponse!!.valute!!.entries) {
                    val model = value.value
                    callbackList.add(model)
                }
                callback(callbackList)

            }

            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    fun getFromId(currencyId: String, callback: (CurrencyModel) -> Unit) {
        val call = api.getAllPosts()
        call!!.enqueue(object : Callback<CurrencyResponse> {
            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                val currencyResponse = response.body()
                for (value in currencyResponse!!.valute!!.entries) {
                    val model = value.value
                    if(model.id == currencyId) {
                        callback(model)
                    }
                }

            }

            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}