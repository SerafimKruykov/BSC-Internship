package com.example.note

import com.example.note.models.Note

interface Communicator {
    fun passData(note: Note?)
}