package com.ishikota.dribbbleclientandroid.data.repository

import android.content.Context
import android.util.Log
import com.ishikota.dribbbleclientandroid.data.api.DribbbleApiClient
import com.ishikota.dribbbleclientandroid.data.api.DribbbleApiClientImpl
import com.ishikota.dribbbleclientandroid.data.api.entities.Shot
import com.ishikota.dribbbleclientandroid.data.preference.DribbblePreferenceImpl
import com.ishikota.dribbbleclientandroid.okhttp.buildDefaultOkHttpClient
import com.squareup.moshi.Moshi
import io.reactivex.Single

class ShotsRepositoryImpl(
    private val apiClient: DribbbleApiClient
) : ShotsRepository {

    override fun getShots(page: Int): Single<List<Shot>> = apiClient.getShots(page, PER_PAGE)

    override fun getShot(id: Int): Single<Shot> = apiClient.getShot(id)

    companion object {
        const val PER_PAGE = 30
    }

    object Factory {
        fun create(context: Context): ShotsRepository {
            val preference = DribbblePreferenceImpl(context)
            val moshi = Moshi.Builder().build()
            val okHttpClient = buildDefaultOkHttpClient(preference) {
                Log.e("TODO", "401401!!")
            }
            val apiClient = DribbbleApiClientImpl.Factory.create(okHttpClient, moshi)
            return ShotsRepositoryImpl(apiClient)
        }
    }
}
