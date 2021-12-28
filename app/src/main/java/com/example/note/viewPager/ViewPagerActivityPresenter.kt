package com.example.note.viewPager

import com.example.note.data.Note
import com.example.note.data.RepositoryContract

class ViewPagerActivityPresenter(
    private val view: ViewPagerActivityView,
    private val repository: RepositoryContract,
    private val addingMode: Boolean
    ) {

    private lateinit var currentNoteList: List<Note>

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToSaveOrUpdate(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSavedBtnPressed()
    }

    /**
     * Обработка нажатия на кнопку "Поделиться"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToShare(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.shareNote(header, content)
    }

    /**
     * Возвращает список заметок из базы данных
     */
    fun getList(): List<Note>{
        val noteList = repository.getData()
        return if(addingMode){
            val newNoteList = mutableListOf<Note>()
            newNoteList.addAll(noteList)
            newNoteList.add(Note(0,"","",""))
            currentNoteList = newNoteList
            newNoteList
        } else{
            currentNoteList = noteList
            noteList
        }
    }

    /**
     * Обработка нажатия на кнопку "Сохранить" из диалога сохранения
     * @param position позиция редактируемой (создаваемой) заметки
     * @param isAdding режим, в котором вошли в пейджер
     * @param noteId id заметки
     * @param header название заметки
     * @param content текст заметки
     * @param time время заметки
     */
    fun handleDialogPositiveClick(
        position: Int,
        isAdding: Boolean,
        noteId: Int,
        header: String?,
        content: String?,
        time: String)
    {
        val note = Note(
            if(position == currentNoteList.size -1 && isAdding) 0 else noteId,
            header,
            content,
            time
        )
        if(position == currentNoteList.size -1 && isAdding) repository.insertNote(note) else repository.updateNote(note)
    }
}