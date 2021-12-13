package com.example.note

import com.example.note.models.Note

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

    /**
     * Вызывается при создании фрагмента, устанавливает передаваемые фрагменту данные в его View
     * @param note передеваемые данные
     */
    fun setNote(note: Note?){
            noteView?.fillViews(note?.header, note?.content, note?.time)
    }
}