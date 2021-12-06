package com.example.note

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment(), NoteView {

    private lateinit var noteNameTextView: TextView
    private lateinit var noteTextTextView: TextView
    private lateinit var timeTextView: TextView

    private lateinit var presenter: MainActivityPresenter

    companion object{
        private const val NAME_KEY: String  = "name"
        private const val TEXT_KEY: String  = "text"
        private const val TIME_KEY: String  = "time"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkInstanceState(savedInstanceState)
        initViews()
        presenter = MainActivityPresenter(this, null)
        noteNameTextView.text = arguments?.getString("header")
        noteTextTextView.text = arguments?.getString("content")
        timeTextView.text = arguments?.getString("time")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        return view

    }

    private fun initViews() {
        noteNameTextView = requireView().findViewById(R.id.noteNameEditText)
        noteTextTextView = requireView().findViewById(R.id.noteTextEditText)
        timeTextView = requireView().findViewById(R.id.timeTextView)
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
        Toast.makeText(context,getString(R.string.toast_savedMessage), Toast.LENGTH_SHORT).show()
    }

    override fun onEmptyNote() {
        Toast.makeText(context,getString(R.string.toast_emptyMessage),Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
        Toast.makeText(context,getString(R.string.toast_errorMessage),Toast.LENGTH_SHORT).show()
    }

    override fun openAboutScreen() {
        val intent = Intent(context, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun shareNote(header: String, content: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
        })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY,noteNameTextView.text.toString())
        outState.putString(TEXT_KEY,noteTextTextView.text.toString())
        outState.putString(TIME_KEY,timeTextView.text.toString())
    }

    private fun checkInstanceState(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            noteNameTextView.text = ""
            noteTextTextView.text = ""
            timeTextView.text = ""
        }else{
            noteNameTextView.text = savedInstanceState.getString(NAME_KEY)
            noteTextTextView.text = savedInstanceState.getString(TEXT_KEY)
            timeTextView.text = savedInstanceState.getString(TIME_KEY)
        }
    }
}