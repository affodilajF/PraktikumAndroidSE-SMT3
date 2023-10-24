package com.example.tugaspertemuan9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.tugaspertemuan9.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2
    private lateinit var binding : ActivityMainBinding
    lateinit var mediator: TabLayoutMediator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            viewPager2 = viewPager

            viewPager.adapter = TabAdapter(supportFragmentManager, this@MainActivity.lifecycle)

            mediator = TabLayoutMediator(tabLayout, viewPager)
            { tab, position->
                when(position){
                    0->tab.text = "Register"
                    1->tab.text = "Login"
                }
            }
            mediator.attach()
        }

    }


}