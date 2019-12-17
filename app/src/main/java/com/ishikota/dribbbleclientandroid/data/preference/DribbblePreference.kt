package com.ishikota.dribbbleclientandroid.data.preference

interface DribbblePreference {
    fun saveAccessToken(accessToken: String)
    fun getAccessToken(): String?
    fun deleteAccessToken()
}
