package com.example.note.viewPager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note.data.NoteRepository

class PagerViewModelFactory(private val repository: NoteRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PagerViewModel(repository) as T
    }
}