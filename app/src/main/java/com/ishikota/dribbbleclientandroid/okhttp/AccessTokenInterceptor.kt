package com.ishikota.dribbbleclientandroid.okhttp

import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreference
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Append Authorization header to request if accessToken is saved in passed preference.
 */
class AccessTokenInterceptor(private val preference: DribbblePreference) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val savedToken = preference.getAccessToken()
        val request = if (savedToken != null) {
            // append saved token to header
            chain.request().newBuilder()
                .removeHeader(HEADER_KEY)
                .addHeader(HEADER_KEY, buildHeaderValue(savedToken))
                .build()
        } else {
            // do nothing if no token is saved
            chain.request()
        }
        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_KEY = "Authorization"
        private fun buildHeaderValue(accessToken: String) = "Bearer $accessToken"
    }
}
