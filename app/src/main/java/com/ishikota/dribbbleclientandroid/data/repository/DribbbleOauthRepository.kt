package com.ishikota.dribbbleclientandroid.data.repository

import io.reactivex.Completable

interface DribbbleOauthRepository {
    fun fetchAccessTokenAndSave(code: String): Completable
}
