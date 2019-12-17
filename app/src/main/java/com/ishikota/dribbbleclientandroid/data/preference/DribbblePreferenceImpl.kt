package com.ishikota.dribbbleclientandroid.data.preference

import android.content.Context
import androidx.core.content.edit

class DribbblePreferenceImpl(context: Context) : DribbblePreference {

    private val preference = context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE)

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
        private const val KEY_PREF_NAME = "dribbble_preference"
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }
}
