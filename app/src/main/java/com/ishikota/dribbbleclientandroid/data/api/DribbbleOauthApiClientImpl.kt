package com.ishikota.dribbbleclientandroid.data.api

import com.ishikota.dribbbleclientandroid.BuildConfig
import com.squareup.moshi.Moshi
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class DribbbleOauthApiClientImpl(
    private val service: DribbbleOauthService
) : DribbbleOauthApiClient {

    override fun requestAccessToken(code: String): Single<String> =
        service.postToken(
            BuildConfig.DRIBBBLE_CLIENT_ID,
            BuildConfig.DRIBBBLE_CLIENT_SECRET,
            code
        ).map {
            it.accessToken
        }

    object Factory {

        fun create(okHttpClient: OkHttpClient, moshi: Moshi): DribbbleOauthApiClientImpl {
            val service = buildService(okHttpClient, moshi)
            return DribbbleOauthApiClientImpl(service)
        }

        private fun buildService(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): DribbbleOauthService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.DRIBBBLE_OAUTH_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(DribbbleOauthService::class.java)
        }
    }
}
