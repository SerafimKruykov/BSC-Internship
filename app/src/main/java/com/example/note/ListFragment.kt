package com.example.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.data.Note
import com.example.note.data.NoteRepository
import com.example.note.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment(R.layout.fragment_list), NotesListView {

    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var adapter: NotesAdapter

    private lateinit var presenter: ListFragmentPresenter
    private lateinit var communicator: Communicator
    private lateinit var repository: NoteRepository

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        communicator = activity as Communicator
        repository = NoteRepository(requireContext())
        presenter = ListFragmentPresenter(this, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        addButton = binding.addButton.also {
            it.setOnClickListener{
                presenter.tryToCreateNote()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.submitList(presenter.getDataFromModel())
    }

    private fun initRecyclerView(){
        notesRecyclerView = binding.notesRecyclerView
        adapter = NotesAdapter {note -> presenter.tryToOpen(note)}

        notesRecyclerView.adapter = adapter
        notesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_about -> {
                presenter.showAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun openNote(note: Note) {
        communicator.passData(note)
        Log.i("onclick1", "${note.header} in communicator")
    }

    override fun openNewNote() {
        communicator.addNote()
    }

    override fun onError() {
        Toast.makeText(context,getString(R.string.toast_errorMessage),Toast.LENGTH_SHORT).show()
    }

    override fun openAboutScreen() {
        val intent = Intent(context, AboutActivity::class.java)
        startActivity(intent)
    }
}

