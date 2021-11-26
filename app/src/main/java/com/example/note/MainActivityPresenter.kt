package com.example.note

class MainActivityPresenter(private val view: NoteView){

    fun tryToSave(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.onSaved()
    }

    fun showAbout(){
        view.openAboutScreen()
    }

    fun tryToShare(header: String, content: String){
        if(header.isEmpty() && content.isEmpty()) view.onEmptyNote() else view.shareNote(header, content)
    }

}