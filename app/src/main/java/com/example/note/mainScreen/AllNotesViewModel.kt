package com.example.note.mainScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.note.SingleLiveEvent
import com.example.note.data.Note
import com.example.note.data.RepositoryContract

class AllNotesViewModel(private val repository: RepositoryContract) : ViewModel() {

    var notes = MutableLiveData<List<Note>>()
    var currentNote = MutableLiveData<Note>()

    init {
        loadAllNotes()
    }

    // Events
    val onAboutBtnPressed = SingleLiveEvent<Unit>()
    val onNotePressed = SingleLiveEvent<Unit>()
    val onAddBtnPressed = SingleLiveEvent<Unit>()

    fun loadAllNotes() {
        notes.value = repository.getData()
    }

    /**
     * Меняет значение лайфдаты notes в зависмости от текста, введенного в SearchView
     * @param query поисковый запрос
     */
    fun searchNotes(query: String?) {
        if (query?.isNotEmpty() == true) {
            notes.value
                ?.filter { note ->
                    note.run {
                        header?.contains(other = query, ignoreCase = true) == true ||
                                content?.contains(other = query, ignoreCase = true) == true
                    }
                }
                .also { notesList ->
                    notes.value = notesList
                }
        } else {
            loadAllNotes()
        }
    }

    /**
     * Обработка нажатия на элемент списка заметок
     * @param note информация о заметке
     */
    fun tryToOpen(note: Note) {
        currentNote.value = note
        onNotePressed.call()
    }

    /**
     * Открывает активити с пустым первым элементом
     */
    fun tryToCreateNote() {
        onAddBtnPressed.call()
    }

    /**
     * Скачивает заметку
     */
    fun downloadNote() {
        repository.getNote()
        notes.value = repository.getData()
    }

    /**
     * Открывает активити информацией о заметке
     */
    fun openAbout() {
        onAboutBtnPressed.call()
    }
}