package com.example.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.note.data.Note

/**
 * Адаптер RecyclerView в ListFragment
 * @param onClick метод, который передастся в конструктор адаптера при его инициализации, обрабатывает нажатие на элемент списка
 */
class NotesAdapter(private val onClick: (Note) -> Unit):
    ListAdapter<Note, NotesAdapter.NotesViewHolder>(Callback()) {

    /**
     * Держит представление элемента списка
     * @param itemView элемент списка
     * @param onClick метод, который передастся в конструктор адаптера при его инициализации, обрабатывает нажатие на элемент списка
     */
    inner class NotesViewHolder(itemView: View, val onClick: (Note) -> Unit) : RecyclerView.ViewHolder(itemView){

        val headerTextView: TextView= itemView.findViewById(R.id.headerTextView)
        val contentTextView: TextView= itemView.findViewById(R.id.contentTextView)
        var currentNote: Note? = null

        init {
            itemView.setOnClickListener{
                currentNote?.let {
                    onClick(it)
                }
            }
        }
    }

    /**
     * Вызывается когда RecyclerView нужно отобразить обьект
     * @param parent родительская разметка
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_item, parent, false), onClick
        )
    }

    /**
     * Связывает данные с представлением
     * @param holder вьюхолдер этого адаптера
     * @param position позиция элемента в списке
     */
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note: Note = getItem(position)
        holder.headerTextView.text = note.header
        holder.contentTextView.text = note.content
        holder.currentNote = note
    }
}

/**
 * Коллбек для динамической работы
 */
class Callback: DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.header == newItem.header
    }
}