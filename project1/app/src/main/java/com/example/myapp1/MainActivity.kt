package com.example.myapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){
            val buttonHitung = binding.btnCountBmi

            buttonHitung.setOnClickListener {
                try {
                    val inputValuebb = textFieldBB.editText?.text.toString().toDouble()
                    val inputValuetb = textFieldTB.editText?.text.toString().toDouble()

                    val bmi = (inputValuebb / (inputValuetb * inputValuetb))

                    textCountedBmi.text = bmi.toString()

                } catch (e: NumberFormatException) {
                    Toast.makeText(this@MainActivity, "Masukkan berat atau tinggi yang valid", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}