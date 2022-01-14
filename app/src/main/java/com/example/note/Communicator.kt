package com.example.note

import com.example.note.data.Note

interface Communicator {
    fun openNote(note: Note?)
    fun addNote()
}