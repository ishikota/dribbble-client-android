package com.ishikota.dribbbleclientandroid.data.api

import io.reactivex.Single

interface DribbbleOauthApiClient {
    fun requestAccessToken(code: String): Single<String>
}
