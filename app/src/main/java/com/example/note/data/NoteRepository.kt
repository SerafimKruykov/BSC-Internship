package com.example.note.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.note.R
import com.example.note.data.api.ResponseModel
import com.example.note.data.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteRepository(private val context: Context) : RepositoryContract {

    private val noteDao = NoteDatabase.getDatabase(context).noteDao()

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
        RetrofitInstance.api.getNote().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val apiResponse = response.body()
                insertNote(
                    Note(
                        0,
                        if (apiResponse?.title != null) apiResponse.title else "",
                        if (apiResponse?.body != null) apiResponse.body else "",
                        context.getString(R.string.note_sample_time)
                    )
                )
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(
                    context,
                    context.getString(R.string.download_faled),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }
}