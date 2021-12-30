package com.example.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.data.Note
import com.example.note.data.NoteRepository
import com.example.note.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list), NotesListView {

    private lateinit var presenter: ListFragmentPresenter
    private lateinit var communicator: Communicator

    private var binding: FragmentListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        communicator = activity as Communicator
        presenter = ListFragmentPresenter(this, NoteRepository(requireContext()))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun initView(){
        binding?.notesRecyclerView?.apply {
            adapter = NotesAdapter {note -> presenter.tryToOpen(note)}.apply {
                submitList(presenter.getDataFromModel())
            }
            layoutManager = LinearLayoutManager(context)
        }
        binding?.addButton.also {
            it?.setOnClickListener{
                presenter.tryToCreateNote()
            }
        }
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

    override fun onStart() {
        super.onStart()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

