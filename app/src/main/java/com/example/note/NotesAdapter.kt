package com.example.note

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.note.models.Note
import java.security.AccessController.getContext

class NotesAdapter(private val onClick: (Note) -> Unit):
    ListAdapter<Note, NotesAdapter.NotesViewHolder>(Callback()) {

   inner class NotesViewHolder(itemView: View, val onClick: (Note) -> Unit) : RecyclerView.ViewHolder(itemView){

        val headerTextView: TextView= itemView.findViewById(R.id.headerTextView)
        val contentTextView: TextView= itemView.findViewById(R.id.contentTextView)
        val currentNote: Note? = null

        init {
           itemView.setOnClickListener{
               currentNote?.let {
                   onClick(currentNote)
               }
           }
        }

   }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_item, parent, false), onClick
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note: Note = getItem(position)
        holder.headerTextView.text = note.header
        holder.contentTextView.text = note.content
    }


}

   class Callback: DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        TODO("Not yet implemented")
    }

}