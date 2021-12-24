package com.example.note

import android.content.Context
import android.util.Log
import com.example.note.data.Note
import com.example.note.data.NoteRepository


/**
 * Класс слушает события во view и обрабатывает их
 * @param notesListView фрагмент, реализующий интерфейс NotesListView
 */

class ListFragmentPresenter(private val notesListView: NotesListView?,private val repository: NoteRepository){

    /**
     * Достает список заметок из базы данных
     */
    fun getDataFromModel(): List<Note>{
        return repository.getData()
    }

    /**
     * Открывает активити с информацией о приложении
     */
    fun showAbout(){
        notesListView?.openAboutScreen()
    }

    /**
     * Обработка нажатия на элемент списка заметок
     * @param note информация о заметке
     */
    fun tryToOpen(note: Note){
        notesListView?.openNote(note)
        Log.i("onclick", "${note.header} was clicked")
    }

    /**
     * Открывает активити с пустым первым элементом
     */
    fun tryToCreateNote(){
        notesListView?.openNewNote()
    }
}