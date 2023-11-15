package com.example.myapplicationapi.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getInstance(): ApiService {

//        logging buat debbuging API
        val interceptor = HttpLoggingInterceptor().
        setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

//        untuk menjalankan, menambah base url kmrn
        val builder = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return builder.create(ApiService::class.java)


    }
}