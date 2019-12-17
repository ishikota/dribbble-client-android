package com.ishikota.dribbbleclientandroid

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreference
import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreferenceImpl

class SplashActivity: AppCompatActivity() {

    private lateinit var preference: DribbblePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = DribbblePreferenceImpl(this)
        if (preference.getAccessToken() != null) {
            startApp()
        } else {
            goLoginPage()
        }
        finish()
    }

    private fun startApp() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun goLoginPage() {
        val uri = Uri.parse(BuildConfig.DRIBBBLE_OAUTH_URI).buildUpon()
            .appendQueryParameter("client_id", BuildConfig.DRIBBBLE_CLIENT_ID)
            .appendQueryParameter("scope", "public write")
            .appendQueryParameter("redirect_uri", BuildConfig.DRIBBBLE_OAUTH_REDIRECT_URI)
            .build()
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
