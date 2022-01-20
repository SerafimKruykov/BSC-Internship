package com.example.note.data

interface RepositoryContract {
    fun getData(): List<Note>
    fun insertNote(note: Note)
    fun updateNote(note: Note)
    fun getNote()
}