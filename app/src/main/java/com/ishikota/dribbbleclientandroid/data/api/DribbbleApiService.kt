package com.ishikota.dribbbleclientandroid.data.api

import com.ishikota.dribbbleclientandroid.data.api.entities.Shot
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DribbbleApiService {

    @GET("/v2/user/shots")
    fun getShots(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<Shot>>

    @GET("/v2/shots/{id}")
    fun getShot(
        @Path("id") id: Int
    ): Single<Shot>
}
