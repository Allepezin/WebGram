package com.browser.webgram.database.room

import androidx.lifecycle.LiveData
import com.browser.webgram.database.DatabaseRepository
import com.browser.webgram.database.models.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao) : DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun deleteAll(onSuccess: () -> Unit) {
        appRoomDao.deleteAll()
        onSuccess()
    }
}
