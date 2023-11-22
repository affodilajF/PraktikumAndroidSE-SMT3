package com.example.myapplication.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name="description")
    val desc : String,

    @ColumnInfo(name="category")
    val category : String,

    @ColumnInfo(name="status")
    val status : String,
    ) : Serializable
