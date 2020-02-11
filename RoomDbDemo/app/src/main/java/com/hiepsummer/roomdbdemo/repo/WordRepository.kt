package com.hiepsummer.roomdbdemo.repo

import androidx.lifecycle.LiveData
import com.hiepsummer.roomdbdemo.WordDao
import com.hiepsummer.roomdbdemo.model.Word

class WordRepository(private val worldDao: WordDao) {
    val allworlds: LiveData<List<Word>> = worldDao.getAlphabetizedWorlds()

    suspend fun insert(world: Word) {
        worldDao.insert(world)
    }

}