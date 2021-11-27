package com.example.note

/**
 * Класс слушает события во view и обрабатывает их
 * @param view - ссылка на активити, которая реализует интерфейс NoteView
 */

class MainActivityPresenter(private val view: NoteView){

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header - название заметки
     * @param content - текст заметки
     */
    fun tryToSave(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSaved()
    }

    /**
     * Обработка нажатия на кнопку "Информация"
     */
    fun showAbout(){
        view.openAboutScreen()
    }

    /**
     * Обработка нажатия на кнопку "Поделиться"
     * @param header - название заметки
     * @param content - текст заметки
     */
    fun tryToShare(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.shareNote(header, content)
    }

}