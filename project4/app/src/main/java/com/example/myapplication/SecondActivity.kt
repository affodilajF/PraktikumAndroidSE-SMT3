package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.MainActivity.Companion.EXTRA_USERNAME
import com.example.myapplication.MainActivity.Companion.EXTRA_PHONE
import com.example.myapplication.MainActivity.Companion.EXTRA_PASSWORD
import com.example.myapplication.MainActivity.Companion.EXTRA_EMAIL



import com.example.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySecondBinding

    private val TAG = "SecondActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate dipanggil")

        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val phone = intent.getStringExtra(EXTRA_PHONE)
        val password = intent.getStringExtra(EXTRA_PASSWORD)

        binding.txtUsername.text = "Wellcome $username"
        binding.txtEmail.text = "Your $email has been activated"
        binding.txtPhone.text = "Your $phone has been registered"

        with(binding){
            btnLogout.setOnClickListener(){
                setResult(Activity.RESULT_CANCELED)
                finish()
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