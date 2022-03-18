package com.browser.webgram.database

import androidx.lifecycle.LiveData
import com.browser.webgram.database.models.AppNote

interface DatabaseRepository {
    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess: () -> Unit)
    suspend fun deleteAll(onSuccess: () -> Unit)
}
