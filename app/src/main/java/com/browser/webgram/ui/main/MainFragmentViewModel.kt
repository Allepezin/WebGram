package com.browser.webgram.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.browser.webgram.constants.REPOSITORY
import com.browser.webgram.database.room.AppRoomDatabase
import com.browser.webgram.database.room.AppRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    fun initDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
            REPOSITORY = AppRoomRepository(dao)
        }
    }
}
