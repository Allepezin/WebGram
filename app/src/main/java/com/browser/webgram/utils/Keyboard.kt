package com.browser.webgram.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object Keyboard {

    fun showKeyboard(editText: EditText) {
        val context = editText.context
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideKeyboard(editText: EditText) {
        val context = editText.context
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    fun EditText.afterTextChangedFlow(): Flow<Editable?> {
        return callbackFlow {
            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    this@callbackFlow.trySend(s).isSuccess
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // to do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // to do nothing
                }
            }

            addTextChangedListener(watcher)
            awaitClose { removeTextChangedListener(watcher) }
        }
    }
}
