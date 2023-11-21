package com.example.nasteeysha_notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasteeysha_notes.utils.Constants.Keys.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val subtitle: String
//    @ColumnInfo
//    val cDate: LocalDateTime,
//    @ColumnInfo
//    val cDate: Date

) {

}