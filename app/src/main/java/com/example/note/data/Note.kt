package com.example.note.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "header") var header: String?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "time") var time : String?
    ) : Serializable