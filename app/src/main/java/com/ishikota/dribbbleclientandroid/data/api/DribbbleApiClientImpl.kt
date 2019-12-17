package com.ishikota.dribbbleclientandroid.data.api

import com.ishikota.dribbbleclientandroid.BuildConfig
import com.ishikota.dribbbleclientandroid.data.api.entities.Shot
import com.squareup.moshi.Moshi
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class DribbbleApiClientImpl(private val service: DribbbleApiService) : DribbbleApiClient {
    override fun getShots(page: Int, perPage: Int): Single<List<Shot>> =
        service.getShots(page, perPage)

    override fun getShot(id: Int): Single<Shot> = service.getShot(id)

    object Factory {

        fun create(okHttpClient: OkHttpClient, moshi: Moshi): DribbbleApiClientImpl {
            val service = buildService(okHttpClient, moshi)
            return DribbbleApiClientImpl(service)
        }

        private fun buildService(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): DribbbleApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.DRIBBBLE_API_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(DribbbleApiService::class.java)
        }
    }
}
