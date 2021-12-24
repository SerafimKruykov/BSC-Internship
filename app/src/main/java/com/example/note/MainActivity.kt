package com.example.note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.note.data.Note
import com.example.note.viewPager.ViewPagerActivity

class MainActivity : AppCompatActivity(), Communicator {

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
        intent.putExtra("position", note?.id)
        startActivity(intent)
    }

    override fun addNote() {
        val intent = Intent(this, ViewPagerActivity::class.java)
        intent.putExtra("isAdding", true)
        startActivity(intent)
    }

    override fun openPager() {

    }
}