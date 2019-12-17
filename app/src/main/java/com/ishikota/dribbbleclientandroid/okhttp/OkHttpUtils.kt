package com.ishikota.dribbbleclientandroid.okhttp

import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreference
import okhttp3.OkHttpClient

fun buildDefaultOkHttpClient(
    preference: DribbblePreference,
    oauthErrorCallback: () -> Unit
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(AccessTokenInterceptor(preference))
        .addInterceptor(OauthErrorInterceptor(oauthErrorCallback))
        .build()
