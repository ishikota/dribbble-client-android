package com.ishikota.dribbbleclientandroid.data.api

import com.ishikota.dribbbleclientandroid.data.api.entities.AccessTokenHolder
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DribbbleOauthService {
    @FormUrlEncoded
    @POST("/oauth/token")
    fun postToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Single<AccessTokenHolder>
}
