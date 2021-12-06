package com.example.note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.models.Note


class ListFragment : Fragment() {
    lateinit var noteList: List<Note>
    lateinit var notesRecyclerView: RecyclerView
    lateinit var adapter: NotesAdapter

    object NotesData {
        val note1 = Note("Первая заметка","Текст первой заметки","12.30")
        val note2 = Note("Вторая заметка","Текст второй заметки","04.45")
        val note3 = Note("Третья заметка","Текст третей заметки","23.40")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)

    }

    fun initRecyclerView(view: View){
        noteList = listOf(NotesData.note1,NotesData.note2,NotesData.note3)
        notesRecyclerView = view.findViewById(R.id.notesRecyclerView) as RecyclerView
        adapter = NotesAdapter{note -> onItemClick(note)}
        adapter.submitList(noteList)
        notesRecyclerView.adapter = adapter
        notesRecyclerView.layoutManager = LinearLayoutManager(view.context)
    }
    fun onItemClick(note: Note){
        Log.i("onclick", "Note was clicked")
    }
}

