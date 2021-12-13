package com.example.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.note.models.Note

class DetailsFragment : Fragment(R.layout.fragment_details), NoteView {

    private lateinit var noteNameEditText: EditText
    private lateinit var noteTextEditText: EditText
    private lateinit var timeTextView: TextView

    private var presenter : DetailsFragmentPresenter? = DetailsFragmentPresenter(this)

    companion object{
        private const val EXTRA_NOTE: String  = "extra note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        if (arguments != null){
            presenter?.setNote(this.requireArguments().getSerializable(EXTRA_NOTE) as? Note)
        }
    }

    private fun initViews(view: View) {
        noteNameEditText = view.findViewById(R.id.noteNameEditText)
        noteTextEditText = view.findViewById(R.id.noteTextEditText)
        timeTextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val header = noteNameEditText.text.toString()
        val content = noteTextEditText.text.toString()

        return when(item.itemId){
            R.id.menu_save -> {
                presenter?.tryToSave(header, content)
                true
            }
            R.id.menu_share -> {
                presenter?.tryToShare(header, content)
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

    override fun shareNote(header: String, content: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constants.DetailsView.INTENT_TEXT_TYPE
            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
        })
    }

    override fun fillViews(header: String?, content: String?, time: String?) {
        noteNameEditText.setText(header)
        noteTextEditText.setText(content)
        timeTextView.text = time
    }

    override fun onDetach() {
        super.onDetach()
        presenter = null
        Log.i("fragment", "onDetach")
    }
}


