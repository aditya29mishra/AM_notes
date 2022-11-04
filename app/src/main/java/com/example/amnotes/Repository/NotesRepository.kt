package com.example.amnotes.Repository

import androidx.lifecycle.LiveData
import com.example.amnotes.Dao.NotesDao
import com.example.amnotes.Model.Notes

class NotesRepository(val dao: NotesDao) {
    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()
    fun getlowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getmediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun gethighNotes(): LiveData<List<Notes>> = dao.getHighNotes()
    fun insertNotes(notes: Notes) = dao.insertNotes(notes)
    fun deleteNotes(id: Int) = dao.deleteNotes(id)
    fun updateNotes(notes: Notes) = dao.updateNotes(notes)
}