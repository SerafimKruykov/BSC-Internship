package com.example.note.viewPager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.note.Constants
import com.example.note.R
import com.example.note.SaveDialogFragment
import com.example.note.data.Note
import com.example.note.data.NoteRepository


class ViewPagerActivity : AppCompatActivity(), ViewPagerActivityView, SaveDialogFragment.SaveDialogListener{

    companion object{
        private const val PASS_ACTION = "position"
        private const val OPEN_ACTION = "isAdding"
        private const val DIALOG_TAG = "616"
    }

    private lateinit var presenter: ViewPagerActivityPresenter
    private lateinit var repository: NoteRepository
    private lateinit var pager: ViewPager2
    private lateinit var adapter: PagerAdapter
    lateinit var currentFragment: NotePagerFragment

    private lateinit var noteList: List<Note>
    private lateinit var newNoteList: List<Note>
    private var isAdding: Boolean = false
    private var position: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        position = intent.getIntExtra(PASS_ACTION,1) - 1
        isAdding = intent.getBooleanExtra(OPEN_ACTION, false)

        repository = NoteRepository(applicationContext)
        presenter = ViewPagerActivityPresenter(this, repository)

        noteList = presenter.getDataFromModel()
        if(isAdding){
            newNoteList = mutableListOf(Note(0,"","", "06.66"))
            (newNoteList as MutableList<Note>).addAll(noteList)
        }

        pager = findViewById(R.id.pager)
        adapter = PagerAdapter(this)
        pager.adapter = adapter
        pager.post{
            pager.currentItem = if(isAdding) 0 else position
        }
    }

    private inner class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int = if (isAdding) newNoteList.size else noteList.size

        override fun createFragment(position: Int): Fragment{
            val note =  if (isAdding) newNoteList[position] else noteList[position]
            return NotePagerFragment.newInstance(
                note.id,
                note.header,
                note.content,
                note.time
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentView = currentFragment.view
        val header = currentView?.findViewById<EditText>(R.id.noteNameEditText)?.text.toString()
        val content = currentView?.findViewById<EditText>(R.id.noteTextEditText)?.text.toString()

        return when(item.itemId){
            R.id.menu_save -> {
                presenter.tryToSaveOrUpdate(header,content)
                true
            }
            R.id.menu_share -> {

                presenter.tryToShare(header,content)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun shareNote(header: String, content: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constants.DetailsView.INTENT_TEXT_TYPE
            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
        })
    }

    override fun onSavedBtnPressed() {
        val dialog = SaveDialogFragment()
        dialog.show(supportFragmentManager, DIALOG_TAG)
    }

    override fun onEmptyNote() {
        Toast.makeText(applicationContext,getString(R.string.toast_emptyMessage),Toast.LENGTH_SHORT).show()
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val currentView = currentFragment.view

        val note = Note(
            if(pager.currentItem == 0 && isAdding) 0 else currentFragment.id!!,
            currentView?.findViewById<EditText>(R.id.noteNameEditText)?.text.toString(),
            currentView?.findViewById<EditText>(R.id.noteTextEditText)?.text.toString(),
            "06.66"
        )
        if(pager.currentItem == 0 && isAdding) repository.insertNote(note) else repository.updateNote(note)
        dialog.dismiss()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }
}