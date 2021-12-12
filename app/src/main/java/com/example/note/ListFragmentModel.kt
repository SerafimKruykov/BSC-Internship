package com.example.note

import com.example.note.models.Note

class ListFragmentModel {

    object NotesData {
        val note1 = Note("Первая заметка","Текст первой заметки","12.30")
        val note2 = Note("Вторая заметка","Текст второй заметки","04.45")
        val note3 = Note("Третья заметка","Текст третей заметки","23.40")
    }

    fun getData() : List<Note>{
        return listOf(NotesData.note1,NotesData.note2,NotesData.note3)
    }

}