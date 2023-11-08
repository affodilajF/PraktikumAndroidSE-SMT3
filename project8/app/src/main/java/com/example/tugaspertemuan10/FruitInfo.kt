package com.example.tugaspertemuan10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugaspertemuan10.MainActivity.Companion.EXTRA_IMG
import com.example.tugaspertemuan10.MainActivity.Companion.EXTRA_JENIS
import com.example.tugaspertemuan10.MainActivity.Companion.EXTRA_LIKE
import com.example.tugaspertemuan10.MainActivity.Companion.EXTRA_LONGDESC
import com.example.tugaspertemuan10.MainActivity.Companion.EXTRA_NAME
import com.example.tugaspertemuan10.MainActivity.Companion.EXTRA_SHORTDESC
import com.example.tugaspertemuan10.databinding.ActivityFruitInfoBinding

class FruitInfo : AppCompatActivity() {

    private lateinit var binding : ActivityFruitInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFruitInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        with(binding){
            txtName.text = intent.getStringExtra(EXTRA_NAME)
            txtJenis.text = intent.getStringExtra(EXTRA_JENIS)
            txtLike.text = intent.getDoubleExtra(EXTRA_LIKE,0.0).toString()
            txtShortdesc.text = intent.getStringExtra(EXTRA_SHORTDESC)
            txtLongdesc.text = intent.getStringExtra(EXTRA_LONGDESC)

            img.setImageResource(intent.getIntExtra(EXTRA_IMG, 0))

            if(intent.getDoubleExtra(EXTRA_LIKE,0.0) <= 4){
                imageView.setImageResource(R.drawable.lovehitam)
            } else {
                imageView.setImageResource(R.drawable.love)
            }

            btnOke.setOnClickListener{
                val intentToMainActivity = Intent(this@FruitInfo, MainActivity::class.java)
                startActivity(intentToMainActivity)
            }
        }

    }
}


