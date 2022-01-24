package com.example.note.data.backUp

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.note.data.NoteRepository

class BackUpWorker(
    context: Context,
    workerParameters: WorkerParameters
): Worker(context,workerParameters) {
    private val repository = NoteRepository(context)
    override fun doWork(): Result {
        Log.i("worker", "${repository.getData().size} notes saved")
        return Result.success()
    }
}