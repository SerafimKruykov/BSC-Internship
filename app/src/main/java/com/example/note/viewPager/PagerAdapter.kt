package com.example.note.viewPager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.note.data.Note

class PagerAdapter(
    activity: AppCompatActivity,
    private val noteList: List<Note>
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = noteList.size

    override fun createFragment(position: Int): Fragment{
        return NotePagerFragment.newInstance(noteList[position])
    }
}