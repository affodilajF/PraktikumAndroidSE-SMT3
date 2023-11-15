package com.example.myapplicationapi.model

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("strMeal")
    var meal: String,

    @SerializedName("strInstructions")
    var instruksi: String,


    @SerializedName("strCategory")
    var category: String,

)
