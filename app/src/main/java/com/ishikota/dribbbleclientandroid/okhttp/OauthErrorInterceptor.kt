package com.ishikota.dribbbleclientandroid.okhttp

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Invoke callback when 401 response is detected.
 */
class OauthErrorInterceptor(private val callback: () -> Unit): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            callback()
        }
        return response
    }
}
