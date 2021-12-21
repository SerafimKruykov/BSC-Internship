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
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment(R.layout.fragment_list), NotesListView {

    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var adapter: NotesAdapter

    private lateinit var presenter: ListFragmentPresenter
    private lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        communicator = activity as Communicator
        presenter = ListFragmentPresenter(this, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        setAddButtonListener(view)
    }

    private fun initRecyclerView(view: View){
        notesRecyclerView = view.findViewById(R.id.notesRecyclerView) as RecyclerView
        adapter = NotesAdapter {note -> presenter.tryToOpen(note)}

        adapter.submitList(presenter.getDataFromModel())

        notesRecyclerView.adapter = adapter
        notesRecyclerView.layoutManager = LinearLayoutManager(view.context)
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
            R.id.menu_pager -> {
                communicator.openPager()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setAddButtonListener(view: View){
        addButton = view.findViewById(R.id.addButton)
        addButton.setOnClickListener{
            presenter.tryToCreateNote()
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

