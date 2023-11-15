package com.example.myapplicationapi.model

import com.google.gson.annotations.SerializedName

data class Meals(

    @SerializedName("meals")
    var data: List<Data>,
)
