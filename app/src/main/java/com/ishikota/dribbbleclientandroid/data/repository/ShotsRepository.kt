package com.ishikota.dribbbleclientandroid.data.repository

import com.ishikota.dribbbleclientandroid.data.api.entities.Shot
import io.reactivex.Single

interface ShotsRepository {
    fun getShots(page: Int): Single<List<Shot>>
    fun getShot(id: Int): Single<Shot>
}
