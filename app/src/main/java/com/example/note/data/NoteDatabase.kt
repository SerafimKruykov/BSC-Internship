package com.example.note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            val tepmInstance = INSTANCE
            if (tepmInstance != null) {
                return tepmInstance
            }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java, "note_database"
            ).allowMainThreadQueries().build()
            return instance
        }
    }
}
