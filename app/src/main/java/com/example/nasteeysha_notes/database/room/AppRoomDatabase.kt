package com.example.nasteeysha_notes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasteeysha_notes.database.room.dao.NoteRoomDao
import com.example.nasteeysha_notes.model.Note
import com.example.nasteeysha_notes.utils.Constants.Keys.NOTE_DATABASE

@Database(entities = [Note:: class], version = 1, exportSchema = false) //подумать!
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): NoteRoomDao
    companion object {

        @Volatile
        private var INSTANSE:AppRoomDatabase?=null

        fun getInstanse(context: Context) : AppRoomDatabase {
            return if(INSTANSE == null) {
                INSTANSE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    NOTE_DATABASE
                ).build()
                INSTANSE as AppRoomDatabase
            } else INSTANSE as AppRoomDatabase
        }
    }
}