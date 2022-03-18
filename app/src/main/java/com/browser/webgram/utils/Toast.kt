package com.browser.webgram.utils

import android.content.Context
import android.widget.Toast

object Toast {

    fun showMessage(context: Context, string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}
