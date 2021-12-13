package com.example.note

interface NoteView {
    // Save
    fun onSaved()
    fun onEmptyNote()
    fun onError()

    // Share
    fun shareNote(header: String, content: String)

    // Fill View with content
    fun fillViews(header: String?, content: String?, time: String?)
}