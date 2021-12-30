package com.example.note.viewPager

interface ViewPagerActivityView {
    fun shareNote(header: String, content: String)
    fun onSavedBtnPressed()
    fun onEmptyNote()
}