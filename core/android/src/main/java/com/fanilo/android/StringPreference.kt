package com.fanilo.android

import android.content.SharedPreferences
import com.fanilo.domain.IPreference
import javax.inject.Inject

class StringPreference @Inject constructor(
    override val key: String,
    override val preferences: SharedPreferences,
    private val defaultValue: String
) : IPreference<String> {

    override fun get() = try {
        preferences.getString(key, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }

    override fun set(value: String) {
        preferences.edit()
            .putString(key, value)
            .apply()
    }

    override fun isSet() = preferences.contains(key)

    override fun delete() {
        preferences.edit()
            .remove(key)
            .apply()
    }
}