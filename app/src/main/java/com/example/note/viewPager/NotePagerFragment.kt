package com.example.note.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.view.ViewGroup

import com.example.note.R
import com.example.note.data.Note
import com.example.note.databinding.FragmentDetailsBinding

private const val ARG_NOTE = "note"

class NotePagerFragment : Fragment(R.layout.fragment_details) {

    var binding: FragmentDetailsBinding? = null

    var id: Int? = null
    private var passedNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            passedNote = it.getSerializable(ARG_NOTE) as Note
            id = passedNote?.id
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        binding?.noteNameEditText?.setText(passedNote?.header)
        binding?.noteTextEditText?.setText(passedNote?.content)
        binding?.timeTextView?.text = passedNote?.time
    }

    companion object {
        @JvmStatic
        fun newInstance(note: Note?) =
            NotePagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_NOTE, note)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as? ViewPagerActivity)?.currentFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}