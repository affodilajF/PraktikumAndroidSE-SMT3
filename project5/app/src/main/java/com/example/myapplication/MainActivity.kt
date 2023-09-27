package com.example.myapplication

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var presensi : Array<String>

    private var presensidipilih:String = "";
    private var selectedTime: String? = null
    private var selectedDate: String? = null
    private var reason: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

//            datepicker
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ){_, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear+1}/$year"
            }

//            timepicker
            timePicker.setOnTimeChangedListener{view, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            }

//            spinner
            presensi = resources.getStringArray(com.example.myapplication.R.array.listpresensi)
            val adapterPresensi = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_item, presensi)
            adapterPresensi.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )

            spinnerPresensi.adapter = adapterPresensi;
            spinnerPresensi.onItemSelectedListener=
                object  : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id : Long) {
                        presensidipilih = presensi[position]
                        if(presensidipilih == "Hadir tepat waktu"){
                            editTextReasonPresensi.visibility = View.INVISIBLE
                        } else {
                            editTextReasonPresensi.visibility = View.VISIBLE
                            editTextReasonPresensi.text.clear()
                        }
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }

            submitBtn.setOnClickListener(){
                if (selectedTime == null || selectedDate == null) {
                    Toast.makeText(this@MainActivity, "Pilih date dan time dulu!", Toast.LENGTH_SHORT).show()
                } else {
                    if(presensidipilih=="Hadir tepat waktu"){
                        Toast.makeText(this@MainActivity, "Presensi dicatat, jam $selectedTime tanggal $selectedDate", Toast.LENGTH_SHORT).show()
                    } else {
                        reason = editTextReasonPresensi.text.toString()
                        Toast.makeText(this@MainActivity, "Dicatat $presensidipilih pada jam $selectedTime tanggal $selectedDate, dengan alasan : $reason", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}