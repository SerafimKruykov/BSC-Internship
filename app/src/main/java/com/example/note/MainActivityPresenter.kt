package com.example.note

class MainActivityPresenter(var view: View){

    fun tryToSave(header: String, content: String ){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSaved()
    }

    interface View {
        fun onSaved()
        fun onEmptyNote()
        fun onError()
    }
}