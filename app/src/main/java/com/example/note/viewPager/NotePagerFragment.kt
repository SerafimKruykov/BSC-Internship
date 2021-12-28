package com.example.note.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.view.ViewGroup

import android.widget.EditText
import android.widget.TextView
import com.example.note.R
import com.example.note.databinding.FragmentDetailsBinding

private const val ARG_ID = "id"
private const val ARG_HEADER = "header"
private const val ARG_CONTENT = "content"
private const val ARG_TIME = "time"

class NotePagerFragment : Fragment(R.layout.fragment_details) {

    private lateinit var headerET: EditText
    private lateinit var contentET: EditText
    private lateinit var timeTW: TextView

    private var _binding: FragmentDetailsBinding? = null
    val binding get() = _binding!!

    var id: Int? = null
    var content: String? = null
    var header: String? = null
    var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
            content = it.getString(ARG_CONTENT)
            header = it.getString(ARG_HEADER)
            time = it.getString(ARG_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews(){
        headerET = binding.noteNameEditText
        contentET = binding.noteTextEditText
        timeTW = binding.timeTextView
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
                        putInt(ARG_ID, id)
                    }
                    putString(ARG_HEADER, header)
                    putString(ARG_CONTENT, content)
                    putString(ARG_TIME, time)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as? ViewPagerActivity)?.currentFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}