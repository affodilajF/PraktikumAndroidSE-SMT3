package com.example.myapp2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private var num1 : Double = 0.0
    private var num2 : Double = 0.0
    private var operation: String? = null
    private var result : Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

//            KETIKA BUTTON = DIKLIK
            binding.buttonSamadengan.setOnClickListener {
                try {
                    val inputtext1 : String = txtCalculator.text.toString()

                    num1 = inputtext1.substring(0, inputtext1.length - 1).toDouble()
                    operation = inputtext1[inputtext1.length - 1].toString()

                    num2 = txtCalculator2.text.toString().toDouble()


                    when (operation){
                        "/" -> {result = num1/ num2}
                        "%" -> {result = num1% num2}
                        "*" -> {result = num1* num2}
                        "-" -> {result = num1- num2}
                        "+" -> {result = num1+ num2}
                    }

                    txtCalculator2.text = result.toString()
                    txtCalculator.text = ""


                } catch (e: NumberFormatException) {
                    Toast.makeText(this@MainActivity, "Masukkan angka yang valid. Klik C (clear) untuk mengulangi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//  BUTTON NUMBER DAN TITIK DI KLIK
    fun onButtonNumberClick (view: View) {
        val buttonNumberClicked = (view as Button).text.toString()

        with(binding){
            txtCalculator2.text = txtCalculator2.text.toString() + buttonNumberClicked
        }
    }

//  BUTTON OPERASI MATH DI KLIK
    fun onButtonOperationClick (view: View) {
        val buttonOperationClicked = (view as Button).text.toString()
        with(binding) {

            if(txtCalculator2.text.toString() == ""){
                Toast.makeText(this@MainActivity, "Masukkan angka dulu, jangan operator math!", Toast.LENGTH_SHORT).show()
            } else {
                txtCalculator.text = txtCalculator2.text.toString() + buttonOperationClicked
                txtCalculator2.text = ""
            }
        }
    }

//    BUTTON DEL DIKLIK
    fun onButtonDeleteClick(view : View){
        with(binding){
            txtCalculator2.text = txtCalculator2.text.substring(0, txtCalculator2.text.length - 1)
        }
    }

//    BUTTON CLEAR DI KLIK
    fun onButtonClearClick(view: View) {
        clear()
    }

//    FUNGSI CLEAR
    private fun clear() {
        num1 = 0.0
        num2 = 0.0
        operation = null
        result = 0.0

        binding.txtCalculator.text = ""
        binding.txtCalculator2.text = ""
    }


}