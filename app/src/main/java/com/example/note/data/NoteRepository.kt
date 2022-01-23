package com.example.note.data

import android.content.Context
import android.os.StrictMode
import android.widget.Toast
import com.example.note.R
import com.example.note.data.api.RetrofitInstance

class NoteRepository(private val context: Context) : RepositoryContract {

    private val noteDao = NoteDatabase.getDatabase(context).noteDao()

    init {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

    override fun getData(): List<Note> {
        return noteDao.loadAllData()
    }

    override fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override fun getNote() {

        val apiResponse = RetrofitInstance.api.getNote().execute().body()
        if(apiResponse != null){
            insertNote(Note(
                0,
                apiResponse.title,
                apiResponse.body,
                apiResponse.userId.toString() + "." + apiResponse.id.toString()
            ))
        }else{
            Toast.makeText(
                context,
                context.getString(R.string.download_faled),
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}