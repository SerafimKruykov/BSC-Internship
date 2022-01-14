package com.example.note.mainScreen

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.AboutActivity
import com.example.note.Communicator
import com.example.note.R
import com.example.note.data.NoteRepository
import com.example.note.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var viewModel: AllNotesViewModel
    private lateinit var communicator: Communicator
    private lateinit var myAdapter: NotesAdapter

    private var binding: FragmentListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        communicator = activity as Communicator
        viewModel =
            ViewModelProvider(this, AllNotesViewModelFactory(NoteRepository(requireContext()))).get(
                AllNotesViewModel::class.java
            )
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.onAboutBtnPressed.observe(this) {
            openAboutScreen()
        }
        viewModel.onNotePressed.observe(this) {
            openNote()
        }
        viewModel.onAddBtnPressed.observe(this) {
            addNote()
        }
        viewModel.notes.observe(this) {
            myAdapter.submitList(viewModel.notes.value)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun initView() {
        myAdapter = NotesAdapter { note -> viewModel.tryToOpen(note) }.apply {
            submitList(viewModel.notes.value)
        }
        binding?.notesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = myAdapter
        }
        binding?.addButton.also {
            it?.setOnClickListener {
                viewModel.tryToCreateNote()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                viewModel.openAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openNote() {
        communicator.openNote(viewModel.currentNote.value)
    }

    private fun addNote() {
        communicator.addNote()
    }

    private fun openAboutScreen() {
        val intent = Intent(context, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadAllNotes()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

