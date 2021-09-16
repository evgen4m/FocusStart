package com.example.myapplication.app

import android.app.Application
import com.example.myapplication.model.CurrencyApi
import com.example.myapplication.model.CurrencyRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    lateinit var retrofit: Retrofit
    lateinit var repository: CurrencyRepository

    companion object {
        const val BASE_URL = "https://www.cbr-xml-daily.ru"
    }

    override fun onCreate() {
        super.onCreate()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        repository = CurrencyRepository(retrofit.create(CurrencyApi::class.java))

    }

}