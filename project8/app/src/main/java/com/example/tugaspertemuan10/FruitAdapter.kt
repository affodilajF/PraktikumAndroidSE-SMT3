package com.example.tugaspertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspertemuan10.databinding.ItemFruitBinding


typealias OnClickFruit = (Fruit) -> Unit

class FruitAdapter (private var listFruit : List<Fruit>,
                    private val onClickFruit: OnClickFruit) :
                    RecyclerView.Adapter<FruitAdapter.ItemFruitViewHolder>() {

    inner class ItemFruitViewHolder(private val binding : ItemFruitBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Fruit){
            with(binding){
                txtName.text = data.name
                txtJenis.text = data.jenis
                txtLike.text = data.suka.toString() + "K"
                txtShortdesc.text = data.sortdesc
                img.setImageResource(data.imageDrawableId)

                if(data.suka <= 4){
                    imageViewLove.setImageResource(R.drawable.lovehitam)
                } else {
                    imageViewLove.setImageResource(R.drawable.love)
                }

                itemView.setOnClickListener{
                    onClickFruit(data)
                }
            }
        }
    }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFruitViewHolder {
             val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return ItemFruitViewHolder(binding)
         }

         override fun getItemCount(): Int {
             return listFruit.size
         }

         override fun onBindViewHolder(holder: ItemFruitViewHolder, position: Int) {
             holder.bind(listFruit[position])
         }
}