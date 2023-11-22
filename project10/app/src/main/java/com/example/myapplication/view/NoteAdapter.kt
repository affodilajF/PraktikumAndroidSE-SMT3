package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Note
import com.example.myapplication.databinding.NoteItemBinding

typealias OnClickData = (Note) -> Unit

class NoteAdapter(
        private var onClickData : OnClickData) :
    RecyclerView.Adapter<NoteAdapter.ItemNoteViewHolder>() {

    private var listdata : List<Note> = ArrayList()

    inner class ItemNoteViewHolder(private var binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(note: Note) {
            with(binding){

                txtTitle.text = note.title
                txtDesc.text = note.desc

                if(note.status == "Important"){
                    parentLayout.setBackgroundResource(R.drawable.importantstyle)
                } else if (note.status == "Essential"){
                    parentLayout.setBackgroundResource(R.drawable.essentialstyle)
                } else {
                    parentLayout.setBackgroundResource(R.drawable.normalstyle)
                }

                itemView.setOnClickListener{
                    onClickData(note)
                }
            }
        }
    }


    fun updateData(newNotesList: List<Note>) {
        listdata= newNotesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemNoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ItemNoteViewHolder, position: Int) {
        holder.bind(listdata[position])
    }
}