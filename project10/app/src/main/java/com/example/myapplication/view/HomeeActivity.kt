package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.Note
import com.example.myapplication.data.NoteDAO
import com.example.myapplication.data.NoteRoomDatabase
import com.example.myapplication.databinding.ActivityHomeeBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.appcompat.widget.SearchView


class HomeeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeeBinding

    private lateinit var mNotesDao : NoteDAO
    private lateinit var executorService : ExecutorService

    private lateinit var adapterNote : NoteAdapter
    private var allNotesLiveData : LiveData<List<Note>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        adapterNote  = NoteAdapter {
                note: Note ->
            val intent = Intent(this, AddnoteActivity::class.java).apply {
                putExtra("note object", note)
            }
            startActivity(intent)
        }

        with(binding){
            rvNote.apply{
                adapter = adapterNote
                layoutManager = LinearLayoutManager(this@HomeeActivity)
            }
        }


        with(binding){
            btnAdd.setOnClickListener{
                val intent = Intent(this@HomeeActivity, AddnoteActivity::class.java)
                intent.putExtra("SOURCE", "Button Tambah")
                startActivity(intent)
            }
        }

//        filter button
        with(binding){

//          filter is off
            allNotesLiveData = mNotesDao.allNotes
            mNotesDao.getCountOfAllNotes.observe(this@HomeeActivity, Observer{count ->
                txtJumlahnotes.text = count.toString() + " Notes"
            })

//          filter is on
            btnAll.setOnClickListener {
                allNotesLiveData = mNotesDao.allNotes
                getAllNotes(allNotesLiveData)
                mNotesDao.getCountOfAllNotes.observe(this@HomeeActivity, Observer{count ->
                    txtJumlahnotes.text = count.toString() + " Notes"
                })

            }
            btnImportant.setOnClickListener {
                allNotesLiveData = mNotesDao.importantNotes
                getAllNotes(allNotesLiveData)
                mNotesDao.getCountOfImportantNotes.observe(this@HomeeActivity, Observer{count ->
                    txtJumlahnotes.text = count.toString() + " Notes"
                })
            }

            btnNormal.setOnClickListener {
                allNotesLiveData = mNotesDao.normalNotes
                getAllNotes(allNotesLiveData)
                mNotesDao.getCountOfNormalNotes.observe(this@HomeeActivity, Observer{count ->
                    txtJumlahnotes.text = count.toString() + " Notes"
                })
            }

            btnEssential.setOnClickListener {
                allNotesLiveData = mNotesDao.essentialNotes
                getAllNotes(allNotesLiveData)
                mNotesDao.getCountOfEssentialNotes.observe(this@HomeeActivity, Observer{count ->
                    txtJumlahnotes.text = count.toString() + " Notes"
                })
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    allNotesLiveData = mNotesDao.searchNotesByTitle(newText)
                    getAllNotes(allNotesLiveData)
                    mNotesDao.getCountOfNotesByTitle(newText).observe(
                        this@HomeeActivity, Observer{count ->
                            txtJumlahnotes.text = count.toString() + " Notes"
                        }
                    )
                    return true
                }
            })
        }
    }

    override fun onResume(){
        super.onResume()
        getAllNotes(allNotesLiveData)
    }
    
    private fun getAllNotes( notes : LiveData<List<Note>>?){
        notes?.observe(this) { notes ->
            notes?.let {
                adapterNote.updateData(notes)
            }
        }
    }
}