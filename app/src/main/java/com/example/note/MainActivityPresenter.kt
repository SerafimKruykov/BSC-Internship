package com.example.note

class MainActivityPresenter(private var view: NoteView){

    fun tryToSave(header: String, content: String ){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSaved()
    }

}