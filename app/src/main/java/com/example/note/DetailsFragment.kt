package com.example.note

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment(), NoteView {

    private lateinit var noteNameTextView: TextView
    private lateinit var noteTextTextView: TextView
    private lateinit var presenter: MainActivityPresenter

    companion object{
        private const val NAME_KEY: String  = "name"
        private const val TEXT_KEY: String  = "text"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        checkInstanceState(savedInstanceState)
        initViews()
        presenter = MainActivityPresenter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    private fun initViews() {
        noteNameTextView = requireView().findViewById(R.id.noteNameEditText)
        noteTextTextView = requireView().findViewById(R.id.noteTextEditText)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val header = noteNameTextView.text.toString()
        val content = noteTextTextView.text.toString()

        return when(item.itemId){
            R.id.menu_about -> {
                presenter.showAbout()
                true
            }
            R.id.menu_save -> {
                presenter.tryToSave(header, content)
                true
            }
            R.id.menu_share -> {
                presenter.tryToShare(header, content)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaved() {
        TODO("Not yet implemented")
    }

    override fun onEmptyNote() {
        TODO("Not yet implemented")
    }

    override fun onError() {
        TODO("Not yet implemented")
    }

    override fun openAboutScreen() {
        TODO("Not yet implemented")
    }

    override fun shareNote(header: String, content: String) {
        TODO("Not yet implemented")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(DetailsFragment.NAME_KEY,noteNameTextView.text.toString())
        outState.putString(DetailsFragment.TEXT_KEY,noteTextTextView.text.toString())
    }

//    private fun checkInstanceState(savedInstanceState: Bundle?){
//        if(savedInstanceState == null){
//            noteNameTextView.text = ""
//            noteTextTextView.text = ""
//        }else{
//            noteNameTextView.text = savedInstanceState.getString(DetailsFragment.NAME_KEY)
//            noteTextTextView.text = savedInstanceState.getString(DetailsFragment.TEXT_KEY)
//        }
//    }
}