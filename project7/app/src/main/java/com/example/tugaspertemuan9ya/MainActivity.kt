package com.example.tugaspertemuan9ya


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugaspertemuan9ya.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "TravelApp"

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigationViews.setupWithNavController(navController)
        }

    }
}