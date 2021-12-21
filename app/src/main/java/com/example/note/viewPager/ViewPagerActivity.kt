package com.example.note.viewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.note.R
import com.example.note.data.Note

private const val ID = "id"
private const val HEADER = "header"
private const val CONTENT = "content"
private const val TIME = "time"

class ViewPagerActivity : AppCompatActivity() {

    private val notesList = listOf(
        Note(1,"Заметка 1", "Текст заметки 1", "12.30"),
        Note(2,"Заметка 2", "Текст заметки 2", "12.30"),
        Note(3,"Заметка 3", "Текст заметки 3", "12.30"),
        Note(4,"Заметка 4", "Текст заметки 4", "12.30")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val pager = findViewById<ViewPager2>(R.id.pager)
        val adapter = PagerAdapter(this)
        pager.adapter = adapter

    }



    private inner class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment{
            val note = notesList[position]
            return NotePagerFragment.newInstance(
                note.id,
                note.header,
                note.content,
                note.time
            )
        }
    }
}