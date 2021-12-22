package com.example.note.data

import android.content.Context

class NoteRepository(context: Context){

    private val noteDao = NoteDatabase.getDatabase(context).noteDao()

    fun getData() : List<Note> {
        return noteDao.loadAllData()

    }

    fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
}