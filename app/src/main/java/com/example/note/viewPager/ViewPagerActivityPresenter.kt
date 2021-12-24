package com.example.note.viewPager

import com.example.note.data.Note
import com.example.note.data.NoteRepository

class ViewPagerActivityPresenter(private val view: ViewPagerActivityView, private val repository: NoteRepository) {

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToSaveOrUpdate(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSavedBtnPressed()
    }

    /**
     * Возвращает список заметок из базы данных
     */
    fun getDataFromModel(): List<Note>{
        return repository.getData()
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