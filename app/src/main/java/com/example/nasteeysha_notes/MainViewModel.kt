package com.example.nasteeysha_notes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nasteeysha_notes.database.room.AppRoomDatabase
import com.example.nasteeysha_notes.database.room.repository.RoomRepository
import com.example.nasteeysha_notes.model.Note
import com.example.nasteeysha_notes.utils.REPOSITORY
import com.example.nasteeysha_notes.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    fun initDatabase(type: String,  onSuccess: ()->Unit) {
        Log.d("checkData", "MainViewModel init Database with type: $type")
        when(type) {
            TYPE_ROOM->{
                val dao = AppRoomDatabase.getInstanse(context = context). getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note =note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }

        }
    }
    fun  readAllNotes() = REPOSITORY.readAll
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel:: class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}