package com.example.myapplication.view

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.Note
import com.example.myapplication.databinding.ActivityHomeeBinding
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore


class HomeeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeeBinding

    private lateinit var adapterNote : NoteAdapter


    //    create properties to be used to acesses firebase
    private var firestore = FirebaseFirestore.getInstance()
    private var updateId = ""
    private val noteListLiveData : MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    private val noteCollectionRef = firestore.collection("notes")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    getAllNotes(newText)
                    return true
                }
            })
        }

        observeNotes()
        getAllNotes("default")

    }


//    GET data
    private fun getAllNotes( filter : String){


        noteCollectionRef
            .let { if (filter == "default") it else it.whereGreaterThanOrEqualTo( "title", filter).whereLessThanOrEqualTo("title", filter + "\uf8ff") }
            .addSnapshotListener { snapshots, error ->
            if(error != null){
                Log.d("HomeeActivity", "Error listening for notes changes", error)
                return@addSnapshotListener
            }

            val note = arrayListOf<Note>()
            snapshots?.forEach{
                    documentReference ->

                note.add(
                    Note(documentReference.id,
                        documentReference.get("title").toString(),
                        documentReference.get("desc").toString(),
                        documentReference.get("status").toString())
                )
            }


            if(note != null){
                noteListLiveData.postValue(note)
                binding.txtJumlahnotes.text = "Jumlah Note : " + note.size.toString()
            }
        }
    }



    private fun observeNotes(){
        noteListLiveData.observe(this){
                notes ->
            adapterNote.updateData(notes)
        }

    }




}