package com.example.nasteeysha_notes.utils

import com.example.nasteeysha_notes.database.DataBaseRepository
import java.text.SimpleDateFormat
import java.util.Date

const val  TYPE_DATABASE = "type_database"
const val  TYPE_ROOM = "type_room"
const val  TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DataBaseRepository

object Constants {
    object Keys {
        const val NOTE_DATABASE = "note_database"
        const val NOTE_TABLE = "notes_table"
//        const val NOTE_TITLE = "Note title"
//        const val NOTE_SUBTITLE="Note subtitle"
//        const val ADD_NOTE="Добавить заметку"
//        const val TITLE = "Title"
//        const val SUBTITLE = "Subitle"
//        const val WHAT_BD= "Выберите базу данных"
//        const val ROOM_DATABASE = "Room database"
        const val ID = "id"
        const val NONE = "none"
//        const val UPDATE = "Редактировать"
//        const val DELETE = "Удалить"
//        const val NAV_BACK = "Назад"
//        const val EDIT_NOTE = "edit note"
        const val EMPTY = ""
//        const val UPDATE_NOTE = "Обновить"
}

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }

    fun formatDate(date: Date?): String {
        var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date)
    }
}