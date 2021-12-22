package com.example.note.viewPager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.note.R
import com.example.note.SaveDialogFragment
import com.example.note.data.Note
import com.example.note.data.NoteRepository
import java.util.zip.Inflater

private const val ID = "id"
private const val HEADER = "header"
private const val CONTENT = "content"
private const val TIME = "time"

class ViewPagerActivity : AppCompatActivity(), ViewPagerActivityView, SaveDialogFragment.SaveDialogListener {

    private lateinit var presenter: ViewPagerActivityPresenter
    private lateinit var repository: NoteRepository
    private lateinit var pager: ViewPager2
    private var currentView: View? = null
    private var currentViewId: Int = 0

    private val notesList = listOf(
        Note(1,"Заметка 1", "Текст заметки 1", "12.30"),
        Note(2,"Заметка 2", "Текст заметки 2", "12.30"),
        Note(3,"Заметка 3", "Текст заметки 3", "12.30"),
        Note(4,"Заметка 4", "Текст заметки 4", "12.30")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        repository = NoteRepository(applicationContext)
        presenter = ViewPagerActivityPresenter(this, repository)

        pager = findViewById<ViewPager2>(R.id.pager)
        val adapter = PagerAdapter(this)
        pager.adapter = adapter

    }


    private inner class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = notesList.size

        override fun createFragment(position: Int): Fragment{
            val note = notesList[position]
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

        return when(item.itemId){
            R.id.menu_save -> {
//                передать заметку здесь
                presenter.tryToSave("test","test")
                true
            }
            R.id.menu_share -> {

                presenter.tryToShare("test","test")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun shareNote(header: String, content: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
        })
    }

    override fun onSavedBtnPressed() {
        val dialog = SaveDialogFragment()
        dialog.show(supportFragmentManager, "616")
    }

    override fun onEmptyNote() {
        Toast.makeText(applicationContext,getString(R.string.toast_emptyMessage),Toast.LENGTH_SHORT).show()
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {

                val currentFragment = supportFragmentManager.findFragmentByTag("f$position") as NotePagerFragment
                currentView = currentFragment.view
                super.onPageSelected(position)

            }
        })

        Toast.makeText(
            applicationContext,
            "null"+currentView?.findViewById<EditText>(R.id.noteNameEditText)?.text,
            Toast.LENGTH_SHORT).show()

        dialog.dismiss()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }
}