package com.example.note

import com.example.note.data.Note

interface NotesListView {
    fun openNote(note: Note)
    fun onError()
    fun openAboutScreen()
    fun openNewNote()
}