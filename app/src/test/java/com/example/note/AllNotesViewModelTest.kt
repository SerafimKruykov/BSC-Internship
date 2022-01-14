package com.example.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.note.data.NoteRepository
import com.example.note.mainScreen.AllNotesViewModel
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class AllNotesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AllNotesViewModel
    private lateinit var repository: NoteRepository

    @Before
    fun setUp(){
        repository = mock(NoteRepository::class.java)
        viewModel = AllNotesViewModel(repository)
    }

    @Test
    fun loadAllNotes() {
        verify(repository).getData()
    }

    @Test
    fun tryToOpen() {
        var btnPressed = false
        viewModel.onNotePressed.observeForever{
            btnPressed = true
        }
        assertTrue(btnPressed)
    }

    @Test
    fun tryToCreateNote() {
        var btnPressed = false
        viewModel.onAddBtnPressed.observeForever{
            btnPressed = true
        }
        assertTrue(btnPressed)
    }

    @Test
    fun openAbout() {
        var btnPressed = false
        viewModel.onAboutBtnPressed.observeForever{
            btnPressed = true
        }
        assertTrue(btnPressed)
    }
}