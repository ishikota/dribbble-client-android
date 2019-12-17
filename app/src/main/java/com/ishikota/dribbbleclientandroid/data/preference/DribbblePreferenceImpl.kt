package com.ishikota.dribbbleclientandroid.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit

class DribbblePreferenceImpl(private val preference: SharedPreferences): DribbblePreference {

    override fun saveAccessToken(accessToken: String) {
        preference.edit {
            putString(KEY_ACCESS_TOKEN, accessToken)
        }
    }

    override fun getAccessToken(): String? = preference.getString(KEY_ACCESS_TOKEN, null)

    override fun deleteAccessToken() {
        preference.edit {
            remove(KEY_ACCESS_TOKEN)
        }
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }
}
