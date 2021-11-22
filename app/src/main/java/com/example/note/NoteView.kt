package com.example.note

interface NoteView {
    fun onSaved()
    fun onEmptyNote()
    fun onError()
}