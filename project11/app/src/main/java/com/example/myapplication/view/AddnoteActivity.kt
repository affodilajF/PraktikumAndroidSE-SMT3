package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.data.Note
import com.example.myapplication.databinding.ActivityAddnoteBinding
import com.google.firebase.firestore.FirebaseFirestore


class AddnoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddnoteBinding

    private lateinit var statusArray : Array<String>
    private var selectedStatus: String = ""

    //    create properties to be used to acesses firebase
    private var firestore = FirebaseFirestore.getInstance()
    private var updateId = ""
    private val noteListLiveData : MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    private val noteCollectionRef = firestore.collection("notes")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnbck.setOnClickListener{
                finish()
            }
        }

//      Update data
        with(binding){
            if(!(intent.hasExtra("SOURCE"))){

                val note = intent.getSerializableExtra("note object") as Note

                val title = note.title
                val editableTitle = Editable.Factory.getInstance().newEditable(title)
                editTextTitle.text = editableTitle

                val desc = note.desc
                val editableDesc = Editable.Factory.getInstance().newEditable(desc)
                edttxtCatatan.text = editableDesc

                spinnerStatus.visibility = View.GONE
                txtStatus.visibility = View.VISIBLE
                btnok.text = "Update"

                txtStatus.text = note.status
                changeColorStyle(note.status)

//                button update
                btnok.setOnClickListener{
                    val judul = editTextTitle.text.toString()
                    val desc = edttxtCatatan.text.toString()
                    val status = note.status

                    val updateNote = Note(id = note.id, title = judul, desc = desc, status = status)
                    updateNote(updateNote)

                    finish()

                }

                btnDel.setOnClickListener {
                    deleteNote(note)
                    finish()

                }


            } else {

//        Tambah data
                spinnerStatus.visibility = View.VISIBLE
                txtStatus.visibility = View.GONE
                btnDel.visibility = View.GONE

                statusArray = resources.getStringArray(R.array.status)
                val adapterStatus = ArrayAdapter(this@AddnoteActivity, android.R.layout.simple_list_item_1, statusArray)
                adapterStatus.setDropDownViewResource(
                    com.google.android.material.R.layout.support_simple_spinner_dropdown_item
                )

                spinnerStatus.adapter = adapterStatus
                spinnerStatus.onItemSelectedListener =
                    object :AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {

                            selectedStatus = statusArray[position]
                            changeColorStyle(selectedStatus!!)
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }

                //                button done
                btnok.setOnClickListener{
                    val judul = editTextTitle.text.toString()
                    val desc = edttxtCatatan.text.toString()
                    val status = selectedStatus

                    val newNote = Note(title = judul, desc = desc, status = status)
                    addNote(newNote)

                    finish()
                }
            }
        }
    }



    private fun changeColorStyle (status : String){
        with(binding){
            when(status){
                "Important" -> {
                    spinnerStatus.setBackgroundResource(R.drawable.importantstyle)
                    layoutEdttxtCatatan.setBackgroundResource(R.drawable.importantstyle)
                }
                "Essential" -> {
                    spinnerStatus.setBackgroundResource(R.drawable.essentialstyle)
                    layoutEdttxtCatatan.setBackgroundResource(R.drawable.essentialstyle)
                }
                "Normal" -> {
                    spinnerStatus.setBackgroundResource(R.drawable.normalstyle)
                    layoutEdttxtCatatan.setBackgroundResource(R.drawable.normalstyle)
                }
            }
        }
    }

    private fun addNote(note : Note){
        noteCollectionRef.add(note)
            .addOnFailureListener {
                Log.d("MainActivity", "Error adding note : ", it)
            }
    }

    private fun updateNote(note : Note){
        noteCollectionRef.document(note.id).set(note)
            .addOnFailureListener {
                Log.d("MainActivity", "Error updating budget : ", it)
            }
    }

    private fun deleteNote(note: Note){
        noteCollectionRef.document(note.id).delete()
            .addOnFailureListener {
                Log.d("MainActivity", "Error deleting budget : ", it)
            }
    }




}