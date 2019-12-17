package com.ishikota.dribbbleclientandroid.okhttp

import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun buildDefaultOkHttpClient(
    preference: DribbblePreference,
    oauthErrorCallback: () -> Unit
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(AccessTokenInterceptor(preference))
        .addInterceptor(OauthErrorInterceptor(oauthErrorCallback))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
