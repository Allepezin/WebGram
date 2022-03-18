package com.browser.webgram.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.browser.webgram.constants.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteAll {}
        }
    }
}
