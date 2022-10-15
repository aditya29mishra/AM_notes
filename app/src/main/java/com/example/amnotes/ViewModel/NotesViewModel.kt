package com.example.amnotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.amnotes.Dao.NotesDao
import com.example.amnotes.Database.NotesDatabase
import com.example.amnotes.Model.Notes
import com.example.amnotes.Repository.NotesRepository
import java.net.IDN

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao =NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun getlowNotes():LiveData<List<Notes>> =repository.getlowNotes()
    fun getmediumNotes():LiveData<List<Notes>> =repository.getmediumNotes()
    fun gethighNotes():LiveData<List<Notes>> =repository.gethighNotes()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}