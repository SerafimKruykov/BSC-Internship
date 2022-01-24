package com.example.note.viewPager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.note.SingleLiveEvent
import com.example.note.data.Note
import com.example.note.data.RepositoryContract

class PagerViewModel(private val repository: RepositoryContract) : ViewModel() {

    var notes = MutableLiveData<List<Note>>()
    var broadcastNote = MutableLiveData<Note>()

    private lateinit var currentNoteList: List<Note>

    init {
        loadAllNotes()
    }

    // Events
    val onEmptyNote = SingleLiveEvent<Unit>()
    val onSaveSuccess = SingleLiveEvent<Unit>()
    val onShareNote = SingleLiveEvent<Unit>()
    val onSavedBtnPressed = SingleLiveEvent<Unit>()

    /**
     * Обработка нажатия на кнопку "Поделиться"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToShare(header: String, content: String) {
        if (header.isEmpty() && content.isEmpty()) onEmptyNote.call() else onShareNote.call()
    }

    /**
     * Обработка нажатия на кнопку "Сохранить"
     * @param header название заметки
     * @param content текст заметки
     */
    fun tryToSaveOrUpdate(header: String, content: String) {
        if (header.isEmpty() && content.isEmpty()) onEmptyNote.call() else onSavedBtnPressed.call()
    }

    /**
     * Возвращает список заметок из базы данных
     */
    fun getList(addingMode: Boolean): List<Note> {
        val noteList = repository.getData()
        return if (addingMode) {
            val newNoteList = mutableListOf<Note>()
            newNoteList.addAll(noteList)
            newNoteList.add(Note(0, "", "", ""))
            currentNoteList = newNoteList
            newNoteList
        } else {
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
        time: String
    ) {
        val note = Note(
            if (position == currentNoteList.size - 1 && isAdding) 0 else noteId,
            header,
            content,
            time
        )
        if (position == currentNoteList.size - 1 && isAdding) {
            repository.insertNote(note)
            broadcastNote.value = note
            onSaveSuccess.call()
        } else {
            repository.updateNote(note)
        }
    }

    private fun loadAllNotes() {
        notes.value = loadNotes()
    }

    private fun loadNotes(): List<Note> {
        return repository.getData()
    }
}