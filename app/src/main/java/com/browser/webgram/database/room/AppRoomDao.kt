package com.browser.webgram.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.browser.webgram.database.models.AppNote

@Dao
interface AppRoomDao {
    @Query("SELECT * from notes_tables ORDER BY id DESC LIMIT 100") //ORDER BY id DESC LIMIT 3
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote)

    @Query("DELETE FROM notes_tables")
    suspend fun deleteAll()
}
