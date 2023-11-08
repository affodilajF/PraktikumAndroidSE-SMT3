package com.example.tugaspertemuan10

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaspertemuan10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object {
        const val EXTRA_NAME = "extra1"
        const val EXTRA_JENIS = "extra2"
        const val EXTRA_LIKE = "extra3"
        const val EXTRA_SHORTDESC = "extra4"
        const val EXTRA_LONGDESC = "extra5"
        const val EXTRA_IMG = "extra6"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapterFruit = FruitAdapter(generateFruit()) {
                fruit ->
            Toast.makeText(this@MainActivity, "U choose ${fruit.name}",
                Toast.LENGTH_SHORT).show()

            val intent = Intent(this, FruitInfo::class.java).apply {
                        putExtra(EXTRA_NAME, fruit.name)
                        putExtra(EXTRA_JENIS, fruit.jenis)
                        putExtra(EXTRA_LIKE, fruit.suka)
                        putExtra(EXTRA_SHORTDESC, fruit.sortdesc)
                        putExtra(EXTRA_LONGDESC, fruit.longdesc)
                        putExtra(EXTRA_IMG, fruit.imageDrawableId)
            }

            startActivity(intent)
        }


        with(binding){
            rvFruits.apply{
                adapter = adapterFruit
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            toolbar.title = "FruitBox"
        }
    }

    fun generateFruit() : List<Fruit> {
        return listOf(
            Fruit("Pisang", "Ambon", "Pisang adalah buah kuning lezat.", "Pisang, juga dikenal dengan nama ilmiah Musa, adalah salah satu buah paling populer di dunia, dikenal dengan rasanya yang manis dan teksturnya yang lembut. Buah pisang biasanya berwarna kuning ketika matang, meskipun ada juga variasi lain yang memiliki warna kulit yang berbeda. ", 4.6, R.drawable.pisang ),
            Fruit("Kiwi", "Hijau", "Kiwi itu segar dan hijau.", "Buah kiwi adalah buah berkulit berwarna coklat dengan daging hijau yang mengandung banyak serat, vitamin C, dan nutrisi penting lainnya. Rasanya segar, manis, dan sedikit asam, dengan biji-biji kecil yang dapat dimakan. Kiwi sering digunakan dalam hidangan buah segar atau sebagai tambahan dalam salad dan smoothie.",5.0, R.drawable.kiwi ),
            Fruit("Alpukat", "Pluwang", "Alpukat adalah buah dengan kulit hijau dan daging lembut berwarna hijau.", "Alpukat, juga dikenal sebagai \"pear alligator\" karena kulitnya yang kasar, adalah buah berukuran besar dengan kulit hijau yang menutupi daging lembut yang dapat berwarna hijau atau kuning muda, tergantung pada kematangan.", 3.8, R.drawable.alpukat ),
            Fruit("Jeruk", "Bali", "Jeruk adalah buah berkulit tebal yang dikenal dengan rasanya yang segar dan manis. ", "Jeruk adalah buah dari berbagai varietas tanaman jeruk yang tumbuh di seluruh dunia. Buah ini memiliki kulit tebal yang melindungi dagingnya yang berair dan manis. Jeruk umumnya dibagi menjadi beberapa varietas yang termasuk jeruk manis, jeruk keprok, jeruk bali, dan masih banyak lagi. ", 3.3, R.drawable.jeruk ),
            Fruit("Melon", "Cavaillon", "Melon itu segar dan hijau.", "Melon adalah buah berair dan segar yang memiliki daging berwarna oranye atau kuning muda dengan biji kecil di tengahnya. Rasanya manis dan menyegarkan, sehingga sering dikonsumsi sebagai camilan atau dalam berbagai hidangan penutup.",4.9, R.drawable.melon ),
            Fruit("Nanas", "Cayene", "Nanas adalah buah tropis yang memiliki rasa manis dan asam yang khas.", "Nanas adalah buah tropis yang berasal dari Amerika Selatan dan telah menyebar ke seluruh dunia. Buah nanas dikenal dengan kulit kasar dan dagingnya yang manis dan sedikit asam. ", 4.3, R.drawable.nanas ),
        )

    }
}