package com.example.note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.note.data.Note
import com.example.note.data.NoteDao
import com.example.note.data.NoteDatabase
import com.example.note.viewPager.ViewPagerActivity
import java.io.Serializable

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var listFragment: ListFragment
    private lateinit var detailsFragment: DetailsFragment
    private lateinit var newDetailsFragment: DetailsFragment


    companion object{
        private const val LIST_NAME_KEY: String  = "time"
        private const val EXTRA_NOTE: String  = "extra note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        listFragment = ListFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, listFragment)
            commit()
        }

    }

    override fun passData(note: Note?) {
        detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable(EXTRA_NOTE, note as Serializable)
        detailsFragment.arguments = bundle

        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, detailsFragment)
            addToBackStack(LIST_NAME_KEY)
            commit()
        }
    }

    override fun addNote() {
        newDetailsFragment = DetailsFragment()
        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, newDetailsFragment)
            addToBackStack(LIST_NAME_KEY)
            commit()
        }
    }

    override fun openPager() {
        val intent = Intent(this, ViewPagerActivity::class.java)
        startActivity(intent)
    }
}