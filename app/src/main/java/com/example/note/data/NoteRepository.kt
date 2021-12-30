package com.example.note.data

import android.content.Context

class NoteRepository(context: Context): RepositoryContract{

    private val noteDao = NoteDatabase.getDatabase(context).noteDao()

    override fun getData() : List<Note> {
        return noteDao.loadAllData()
    }

    override fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    override fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
}