package com.example.note.viewPager

import com.example.note.data.Note
import com.example.note.data.NoteRepository

class ViewPagerActivityPresenter(private val view: ViewPagerActivityView, private val repository: NoteRepository) {

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToSave(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSavedBtnPressed()
    }

    fun saveNote(note: Note){
        repository.updateNote(note)
    }

    /**
     * Обработка нажатия на кнопку "Поделиться"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToShare(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.shareNote(header, content)
    }
}