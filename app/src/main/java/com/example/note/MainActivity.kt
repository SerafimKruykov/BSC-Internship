package com.example.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var listFragment: ListFragment
    private lateinit var detailsFragment: DetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFragment = ListFragment()
        detailsFragment = DetailsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, listFragment)
            commit()
        }

    }

    override fun passData(header: String?, content: String?, time: String?) {
        val bundle = Bundle()
        bundle.putString("header", header)
        bundle.putString("content", content)
        bundle.putString("time", time)
        detailsFragment.arguments = bundle

        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, detailsFragment)
            commit()
        }
    }


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