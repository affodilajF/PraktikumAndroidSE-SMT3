package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class],
    version = 1,
    exportSchema = false)

abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDAO?



    companion object {
        @Volatile
        private var INSTANCE : NoteRoomDatabase ? = null

        fun getDatabase(context: Context) : NoteRoomDatabase? {


            if(INSTANCE == null){
                synchronized(NoteRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteRoomDatabase::class.java, "note_db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }


}