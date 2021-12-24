package com.example.note.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View

import android.widget.EditText
import android.widget.TextView
import com.example.note.R


private const val ID = "id"
private const val HEADER = "header"
private const val CONTENT = "content"
private const val TIME = "time"

class NotePagerFragment : Fragment(R.layout.fragment_details) {

    private lateinit var headerET: EditText
    private lateinit var contentET: EditText
    private lateinit var timeTW: TextView

    var id: Int? = null
    var content: String? = null
    var header: String? = null
    var time: String? = "06.66"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
            content = it.getString(CONTENT)
            header = it.getString(HEADER)
            time = it.getString(TIME)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerET = view.findViewById(R.id.noteNameEditText)
        contentET = view.findViewById(R.id.noteTextEditText)
        timeTW = view.findViewById(R.id.timeTextView)
        headerET.setText(header)
        contentET.setText(content)
        timeTW.text = time
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int?, header: String?, content: String?, time: String?) =
            NotePagerFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt(ID, id)
                    }
                    putString(HEADER, header)
                    putString(CONTENT, content)
                    putString(TIME, time)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as? ViewPagerActivity)?.currentFragment = this
    }
}