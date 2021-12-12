package com.example.note

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.note.models.Note

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var listFragment: ListFragment
    private lateinit var detailsFragment: DetailsFragment

    companion object{
        private const val HEADER_KEY: String  = "header"
        private const val CONTENT_KEY: String  = "content"
        private const val TIME_KEY: String  = "time"
        private const val LIST_NAME_KEY: String  = "time"
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
        bundle.putString(HEADER_KEY, note?.header)
        bundle.putString(CONTENT_KEY, note?.content)
        bundle.putString(TIME_KEY, note?.time)
        detailsFragment.arguments = bundle
        Log.i("onclick2", bundle.getString(HEADER_KEY).toString()+" in args")

        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, detailsFragment)
            addToBackStack(LIST_NAME_KEY)
            commit()
        }
    }
}