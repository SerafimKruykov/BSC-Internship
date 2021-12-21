package com.example.note.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.note.R
import com.example.note.data.Note

private const val ID = "id"
private const val HEADER = "header"
private const val CONTENT = "content"
private const val TIME = "time"

class NotePagerFragment : Fragment(R.layout.fragment_details) {

    private lateinit var headetET: EditText
    private lateinit var contentET: EditText
    private lateinit var timeTW: TextView

    private var id: String? = null
    private var content: String? = null
    private var header: String? = null
    private var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ID)
            content = it.getString(CONTENT)
            header = it.getString(HEADER)
            time = it.getString(TIME)
        }

    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_details, container, false)
//
//        return view
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headetET = view.findViewById(R.id.noteNameEditText)
        contentET = view.findViewById(R.id.noteTextEditText)
        timeTW = view.findViewById(R.id.timeTextView)
        headetET.setText(header)
        contentET.setText(content)
        timeTW.text = time
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, header: String?, content: String?, time: String?) =
            NotePagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID, id)
                    putString(HEADER, header)
                    putString(CONTENT, content)
                    putString(TIME, time)
                }
            }
    }
}