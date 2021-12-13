package com.example.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.note.models.Note
import java.io.Serializable

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var listFragment: ListFragment
    private lateinit var detailsFragment: DetailsFragment

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
}