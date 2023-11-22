package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (note : Note)
    @Update
    fun update(note:Note)
    @Delete
    fun delete(note:Note)

    @get:Query("SELECT * from note_table ORDER BY id DESC")
    val allNotes : LiveData<List<Note>>

    @get:Query("SELECT * FROM note_table WHERE status = 'Important' ORDER BY id DESC")
    val importantNotes : LiveData<List<Note>>

    @get:Query("SELECT * FROM note_table WHERE status = 'Essential' ORDER BY id DESC")
    val essentialNotes : LiveData<List<Note>>

    @get:Query("SELECT * FROM note_table WHERE status = 'Normal' ORDER BY id DESC")
    val normalNotes : LiveData<List<Note>>


    @get:Query("SELECT COUNT(*) FROM note_table")
    val getCountOfAllNotes : LiveData<Int>

    @get:Query("SELECT COUNT(*) FROM note_table WHERE status = 'Important'")
    val getCountOfImportantNotes : LiveData<Int>

    @get:Query("SELECT COUNT(*) FROM note_table WHERE status = 'Essential'")
    val getCountOfEssentialNotes : LiveData<Int>

    @get:Query("SELECT COUNT(*) FROM note_table WHERE status = 'Normal'")
    val getCountOfNormalNotes : LiveData<Int>

    @Query("SELECT * FROM note_table WHERE title LIKE '%' || :searchQuery || '%' ORDER BY id DESC")
    fun searchNotesByTitle(searchQuery: String): LiveData<List<Note>>

    @Query("SELECT COUNT(*) FROM note_table WHERE title LIKE '%' || :searchQuery || '%'")
    fun getCountOfNotesByTitle(searchQuery: String): LiveData<Int>




}