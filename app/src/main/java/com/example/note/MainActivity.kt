package com.example.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listFragment = ListFragment()
//        val detailsFragment = DetailsFragment()
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.frameForFragments, listFragment)
//            commit()
//        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ListFragment>(R.id.fragment_list_view)

        }
    }
//    private fun initViews() {
//        noteNameTextView = findViewById(R.id.noteNameEditText)
//        noteTextTextView = findViewById(R.id.noteTextEditText)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        val header = noteNameTextView.text.toString()
//        val content = noteTextTextView.text.toString()
//
//        return when(item.itemId){
//            R.id.menu_about -> {
//                presenter.showAbout()
//                true
//            }
//            R.id.menu_save -> {
//                presenter.tryToSave(header, content)
//                true
//            }
//            R.id.menu_share -> {
//                presenter.tryToShare(header, content)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onSaved() {
//        Toast.makeText(this,getString(R.string.toast_savedMessage),Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onEmptyNote() {
//        Toast.makeText(this,getString(R.string.toast_emptyMessage),Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onError() {
//        Toast.makeText(this,getString(R.string.toast_errorMessage),Toast.LENGTH_SHORT).show()
//    }
//
//    override fun openAboutScreen() {
//        val intent = Intent(this, AboutActivity::class.java)
//        startActivity(intent)
//    }
//
//    override fun shareNote(header: String, content: String) {
//        startActivity(Intent(Intent.ACTION_SEND).apply {
//            type = "text/plain"
//            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
//        })
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(NAME_KEY,noteNameTextView.text.toString())
//        outState.putString(TEXT_KEY,noteTextTextView.text.toString())
//    }
//
//    private fun checkInstanceState(savedInstanceState: Bundle?){
//        if(savedInstanceState == null){
//            noteNameTextView.text = ""
//            noteTextTextView.text = ""
//        }else{
//            noteNameTextView.text = savedInstanceState.getString(NAME_KEY)
//            noteTextTextView.text = savedInstanceState.getString(TEXT_KEY)
//        }
//    }
}