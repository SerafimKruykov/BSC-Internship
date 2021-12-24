package com.example.note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.note.data.Note
import com.example.note.viewPager.ViewPagerActivity

class MainActivity : AppCompatActivity(), Communicator {

    companion object{
        private const val PASS_ACTION = "position"
        private const val OPEN_ACTION = "isAdding"
    }

    private lateinit var listFragment: ListFragment

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
        val intent = Intent(this, ViewPagerActivity::class.java)
        intent.putExtra(PASS_ACTION, note?.id)
        startActivity(intent)
    }

    override fun addNote() {
        val intent = Intent(this, ViewPagerActivity::class.java)
        intent.putExtra(OPEN_ACTION, true)
        startActivity(intent)
    }

    override fun openPager() {

    }
}