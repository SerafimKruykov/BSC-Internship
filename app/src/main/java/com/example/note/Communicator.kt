package com.example.note

import com.example.note.data.Note

interface Communicator {
    fun passData(note: Note?)
    fun addNote()
}