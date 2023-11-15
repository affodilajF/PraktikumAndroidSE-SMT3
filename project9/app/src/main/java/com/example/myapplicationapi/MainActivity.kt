package com.example.myapplicationapi

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.example.myapplicationapi.databinding.ActivityMainBinding
import com.example.myapplicationapi.model.Meals
import com.example.myapplicationapi.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
//
        val client = ApiClient.getInstance()
        val response = client.getAllMeals()

        response.enqueue(object : Callback<Meals> {
            override fun onResponse(call: Call<Meals>, response: Response<Meals>) {

                val meals = arrayListOf<String>()
                val instc = arrayListOf<String>()
                val categories = arrayListOf<String>()

                for (data in response.body()?.data ?: arrayListOf()) {
                    meals.add(data.meal)
                    instc.add(data.instruksi)
                }


                val dataAll = ArrayList<HashMap<String, String>>()

                for (i in meals.indices) {
                    val map = HashMap<String, String>()
                    map["meal"] = meals[i]
                    map["instc"] = instc[i]
                    dataAll.add(map)
                }

                val from = arrayOf("meal", "instc")
                val to = intArrayOf(android.R.id.text1, android.R.id.text2)
                val adapter = SimpleAdapter(
                    this@MainActivity,
                    dataAll,
                    R.layout.simple_list_item_2,
                    from,
                    to
                )
                binding.lsView.adapter = adapter
            }

            override fun onFailure(call: Call<Meals>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        }
}