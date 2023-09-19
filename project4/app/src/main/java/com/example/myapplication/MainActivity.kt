package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivityLifecycle"

    private lateinit var binding : ActivityMainBinding

    companion object{
        const val EXTRA_USERNAME = "extra1"
        const val EXTRA_EMAIL = "extra2"
        const val EXTRA_PHONE = "extra3"
        const val EXTRA_PASSWORD = "extra4"
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_CANCELED) {
            with(binding){
                editTextUsername.text.clear()
                editTxtEmail.text.clear()
                editTxtPhone.text.clear()
                editTxtPassword.text.clear()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate dipanggil")

        with(binding){
            btnRegister.setOnClickListener(){
                val intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java)
                    .apply {
                        putExtra(EXTRA_USERNAME, editTextUsername.text.toString())
                        putExtra(EXTRA_EMAIL, editTxtEmail.text.toString())
                        putExtra(EXTRA_PHONE, editTxtPhone.text.toString())
                        putExtra(EXTRA_PASSWORD, editTxtPassword.text.toString())
                }
                launcher.launch(intentToSecondActivity)
            }
        }
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart dipanggil")

    }

    override fun onRestart(){
        super.onRestart()
        Log.d(TAG, "onRestart dipanggil")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume dipanggil")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause dipanggil")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop dipanggil")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy dipanggil")
    }
}