package com.example.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment(), NoteView {

    private lateinit var noteNameEditText: EditText
    private lateinit var noteTextEditText: EditText
    private lateinit var timeTextView: TextView

    private lateinit var presenter: MainActivityPresenter

    companion object{
        private const val HEADER_KEY: String  = "header"
        private const val CONTENT_KEY: String  = "content"
        private const val TIME_KEY: String  = "time"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainActivityPresenter(this, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        initViews(view)
        return view

    }

    private fun initViews(view: View) {
        noteNameEditText = view.findViewById(R.id.noteNameEditText)
        noteTextEditText = view.findViewById(R.id.noteTextEditText)
        timeTextView = view.findViewById(R.id.timeTextView)
        Log.i("onclick3", "${arguments?.getString(HEADER_KEY)} in details fragment")
        noteNameEditText.setText(arguments?.getString(HEADER_KEY))
        noteTextEditText.setText(arguments?.getString(CONTENT_KEY))
        timeTextView.text = arguments?.getString(TIME_KEY)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val header = noteNameEditText.text.toString()
        val content = noteTextEditText.text.toString()

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

}