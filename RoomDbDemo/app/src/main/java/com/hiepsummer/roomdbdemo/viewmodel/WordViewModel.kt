package com.hiepsummer.roomdbdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hiepsummer.roomdbdemo.repo.WordRepository
import com.hiepsummer.roomdbdemo.db.WordRoomDatabase
import com.hiepsummer.roomdbdemo.model.Word
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository

    val allWorlds: LiveData<List<Word>>

    init {
        val worldDao = WordRoomDatabase.getDatabase(application,viewModelScope).wordDao()
        repository = WordRepository(worldDao)
        allWorlds = repository.allworlds
    }

    fun insert(world: Word) = viewModelScope.launch {
        repository.insert(world)
    }
}