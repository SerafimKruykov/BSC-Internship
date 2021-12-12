package com.example.note

/**
 * Класс слушает события во view и обрабатывает их
 * @param noteView ссылка на фрагмент, которая реализует интерфейс NoteView
 */

class DetailsFragmentPresenter(private val noteView: NoteView?){

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToSave(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) noteView?.onEmptyNote() else noteView?.onSaved()
    }


    /**
     * Обработка нажатия на кнопку "Поделиться"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToShare(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) noteView?.onEmptyNote() else noteView?.shareNote(header, content)
    }
}