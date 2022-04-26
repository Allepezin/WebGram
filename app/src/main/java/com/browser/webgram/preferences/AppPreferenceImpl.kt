package com.browser.webgram.preferences

import android.content.Context
import javax.inject.Inject

class AppPreferenceImpl @Inject constructor(context: Context) : AppPreference {
    companion object {
        const val name_visited = "keyVisited"
        const val name_checked = "keySwitch"
        const val name_settings = "settings"
    }

    private var preference = context.getSharedPreferences(name_settings, Context.MODE_PRIVATE)
    private var editor = preference.edit()

    override var visited: String
        get() = getString(name_visited)
        set(value) {
            saveString(name_visited, value)
        }

    override var checked: Int
        get() = getInt(name_checked)
        set(value) {
            saveInt(name_checked, value)
        }

    private fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return preference.getString(key, defaultValue) ?: defaultValue
    }

    private fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    private fun getInt(key: String, defaultValue: Int = 1): Int {
        return preference.getInt(key, defaultValue) ?: defaultValue
    }
}
