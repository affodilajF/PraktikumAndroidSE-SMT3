package com.example.myapplication.data

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class Note(

    @set:Exclude @get:Exclude @Exclude var id : String = "",

    val title : String = "",
    val desc : String = "",
    val status : String = "",

    ) : Serializable
