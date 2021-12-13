package com.example.note

import com.example.note.models.Note

interface NotesListView {
    fun openNote(note: Note)
    fun onError()
    fun openAboutScreen()
}