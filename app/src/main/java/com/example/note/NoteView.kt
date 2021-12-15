package com.example.note

interface NoteView {
    // Save
    fun onSaved()
    fun onEmptyNote()
    fun onError()

    // About
    fun openAboutScreen()

    // Share
    fun shareNote(header: String, content: String)
}