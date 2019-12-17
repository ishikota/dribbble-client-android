package com.ishikota.dribbbleclientandroid.data.repository

import android.content.Context
import android.util.Log
import com.ishikota.dribbbleclientandroid.data.api.DribbbleOauthApiClient
import com.ishikota.dribbbleclientandroid.data.api.DribbbleOauthApiClientImpl
import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreference
import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreferenceImpl
import com.ishikota.dribbbleclientandroid.okhttp.buildDefaultOkHttpClient
import com.squareup.moshi.Moshi
import io.reactivex.Completable

class DribbbleOauthRepositoryImpl(
    private val apiClient: DribbbleOauthApiClient,
    private val preference: DribbblePreference
) : DribbbleOauthRepository {

    override fun fetchAccessTokenAndSave(code: String): Completable =
        apiClient.requestAccessToken(code)
            .doOnSuccess(preference::saveAccessToken)
            .ignoreElement()

    object Factory {
        fun create(context: Context): DribbbleOauthRepository {
            val preference = DribbblePreferenceImpl(context)
            val moshi = Moshi.Builder().build()
            val okHttpClient = buildDefaultOkHttpClient(preference) {
                Log.e("TODO", "401401!!")
            }
            val apiClient = DribbbleOauthApiClientImpl.Factory.create(okHttpClient, moshi)
            return DribbbleOauthRepositoryImpl(apiClient, preference)
        }
    }
}
