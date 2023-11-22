package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myapplication.R
import com.example.myapplication.data.Note
import com.example.myapplication.data.NoteDAO
import com.example.myapplication.data.NoteRoomDatabase
import com.example.myapplication.databinding.ActivityAddnoteBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddnoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddnoteBinding

    private lateinit var mNotesDao : NoteDAO
    private lateinit var executorService : ExecutorService

    private lateinit var statusArray : Array<String>
    private var selectedStatus: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

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
                    update(
                        Note(id = note.id , title = editTextTitle.text.toString(), desc = edttxtCatatan.text.toString(), category = "", status = note.status)
                    )
                    finish()
                }

                btnDel.setOnClickListener {
                    delete(note)
                    finish()
                }


            } else {

//        Tambah data
                spinnerStatus.visibility = View.VISIBLE
                txtStatus.visibility = View.GONE
                btnDel.visibility = View.GONE

//                button done
                btnok.setOnClickListener{
                    insert(
                        Note(title = editTextTitle.text.toString(), desc = edttxtCatatan.text.toString(), category = "" , status = selectedStatus.toString())
                    )
                    finish()
                }

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

    private fun insert(note : Note){
        val runnable = object : Runnable {
            override fun run() {
                mNotesDao.insert(note)
            }
        }
        executorService.execute(runnable)
    }


    private fun update(note : Note){
        executorService.execute{
            mNotesDao.update(note)
        }
    }

    private fun delete(note : Note){
        executorService.execute{
            mNotesDao.delete(note)
        }
    }

}