package com.example.note

import android.util.Log
import com.example.note.models.Note


/**
 * Класс слушает события во view и обрабатывает их
 * @param notesListView фрагмент, реализующий интерфейс NotesListView
 */

class ListFragmentPresenter(private val notesListView: NotesListView?){

    private val model = ListFragmentModel()

    fun getDataFromModel(): List<Note>{
        return model.getData()
    }

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
}