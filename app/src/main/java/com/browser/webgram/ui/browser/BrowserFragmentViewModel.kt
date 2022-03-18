package com.browser.webgram.ui.browser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.browser.webgram.constants.REPOSITORY
import com.browser.webgram.database.models.AppNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BrowserFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun insert(note: AppNote) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {}
        }
}
