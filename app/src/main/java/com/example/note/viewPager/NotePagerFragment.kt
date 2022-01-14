package com.example.note.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

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
        return DataBindingUtil.inflate<FragmentDetailsBinding>(inflater,R.layout.fragment_details, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.note = passedNote
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