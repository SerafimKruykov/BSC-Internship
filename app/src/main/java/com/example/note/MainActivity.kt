package com.example.note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.note.data.Note
import com.example.note.mainScreen.ListFragment
import com.example.note.viewPager.ViewPagerActivity
import com.google.android.gms.common.GoogleApiAvailability

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this)

        listFragment = ListFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, listFragment)
            commit()
        }

    }

    override fun openNote(note: Note?) {
        val intent = Intent(this, ViewPagerActivity::class.java)
        intent.putExtra(Constants.Transaction.PASS_ACTION, note?.id?.minus(1))
        startActivity(intent)
    }

    override fun addNote() {
        val intent = Intent(this, ViewPagerActivity::class.java)
        intent.putExtra(Constants.Transaction.ADD_ACTION, true)
        startActivity(intent)
    }

    override fun openWebView() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }

    override fun openTextView() {
        startActivity(Intent(this, TextViewActivity::class.java))
    }
}