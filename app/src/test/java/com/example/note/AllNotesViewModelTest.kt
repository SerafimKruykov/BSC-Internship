package com.example.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.note.data.NoteRepository
import com.example.note.mainScreen.AllNotesViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AllNotesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AllNotesViewModel

    @RelaxedMockK
    private lateinit var repository: NoteRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        viewModel = AllNotesViewModel(repository)
    }

    @Test
    fun loadAllNotes() {
        every { repository.getData() } returns listOf()
    }

    @Test
    fun tryToOpen() {
        viewModel.onNotePressed.observeForever(mockk(relaxed = true))
        viewModel.tryToOpen(mockk())
        verify{viewModel.onNotePressed.call()}
    }

    @Test
    fun tryToCreateNote() {
        viewModel.onAddBtnPressed.observeForever(mockk(relaxed = true))
        viewModel.tryToCreateNote()
        verify{viewModel.onAddBtnPressed.call()}
    }

    @Test
    fun openAbout() {
        viewModel.onAboutBtnPressed.observeForever(mockk(relaxed = true))
        viewModel.openAbout()
        verify{viewModel.onAboutBtnPressed.call()}
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}