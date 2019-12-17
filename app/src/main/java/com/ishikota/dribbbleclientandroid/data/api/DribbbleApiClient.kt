package com.ishikota.dribbbleclientandroid.data.api

import com.ishikota.dribbbleclientandroid.data.api.entities.Shot
import io.reactivex.Single

interface DribbbleApiClient {
    fun getShots(page: Int, perPage: Int): Single<List<Shot>>
    fun getShot(id: Int): Single<Shot>
}
