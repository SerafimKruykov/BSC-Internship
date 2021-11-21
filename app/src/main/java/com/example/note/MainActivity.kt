package com.example.note

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), MainActivityPresenter.View {
    private lateinit var noteNameTextView: TextView
    private lateinit var noteTextTextView: TextView
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        checkInstanceState(savedInstanceState)

        presenter = MainActivityPresenter(this)
    }

    private fun initViews() {
        noteNameTextView = findViewById(R.id.noteNameEditText)
        noteTextTextView = findViewById(R.id.noteTextEditText)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_save) {
            presenter.tryToSave(noteNameTextView.text.toString(), noteTextTextView.text.toString())
            return true
        }else {
            super.onOptionsItemSelected(item)
        }
        return true

    }

    override fun onSaved() {
        Toast.makeText(this,getString(R.string.toast_savedMessage),Toast.LENGTH_SHORT).show()
    }

    override fun onEmptyNote() {
        Toast.makeText(this,getString(R.string.toast_emptyMessage),Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
        Toast.makeText(this,getString(R.string.toast_errorMessage),Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outPersistentState.putString("name",noteNameTextView.text.toString())
        outPersistentState.putString("text",noteTextTextView.text.toString())
    }

    private fun checkInstanceState(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            noteNameTextView.text = ""
            noteTextTextView.text = ""
        }else{
            noteNameTextView.text = savedInstanceState.getString("name")
            noteTextTextView.text = savedInstanceState.getString("text")
        }
    }
}