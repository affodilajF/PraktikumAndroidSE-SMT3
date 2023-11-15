package com.example.myapplicationapi.retrofit

import com.example.myapplicationapi.model.Meals
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("search.php?f=a")
    fun getAllMeals() : Call<Meals>

}