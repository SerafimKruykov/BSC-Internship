package com.example.note

import android.util.Log
import com.example.note.models.Note

/**
 * Класс слушает события во view и обрабатывает их
 * @param view ссылка на активити, которая реализует интерфейс NoteView
 */

class MainActivityPresenter(private val noteView: NoteView?, private val notesListView: NotesListView?){

    // Методы для фрагмента редактирования заметки

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToSave(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) noteView?.onEmptyNote() else noteView?.onSaved()
    }

    /**
     * Обработка нажатия на кнопку "Информация"
     */
    fun showAbout(){
        noteView?.openAboutScreen()
    }

    /**
     * Обработка нажатия на кнопку "Поделиться"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToShare(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) noteView?.onEmptyNote() else noteView?.shareNote(header, content)
    }

    // Методы для фрагмента списка заметок

    /**
     * Обработка нажатия на элемент списка заметок
     * @param note информация о заметке
     */

    fun tryToOpen(note: Note){
        notesListView?.openNote(note)
        Log.i("onclick", "Note was clicked")
    }

}