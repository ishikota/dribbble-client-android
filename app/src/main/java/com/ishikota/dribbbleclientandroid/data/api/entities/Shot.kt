package com.ishikota.dribbbleclientandroid.data.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Shot(
    val id: String,
    val title: String,
    val description: String,
    val width: Int,
    val height: Int,
    val images: Images,
    val viewsCount: Int,
    val likesCount: Int,
    val commentsCount: Int,
    val attachmentsCount: Int,
    val createdAt: String,
    val updatedAt: String,
    val htmlUrl: String,
    val attachmentsUrl: String,
    val tags: List<String>,
    val user: User,
    val team: Team

) {
    @JsonClass(generateAdapter = true)
    data class Images(
        val hidpi: String?,
        val normal: String?,
        val teaser: String?
    )

    @JsonClass(generateAdapter = true)
    data class User(
        val id: Int,
        val name: String,
        val userName: String,
        val avatarUrl: String,
        val followersCount: Int,
        val followingCount: Int,
        val likesCount: Int,
        val likesReceivedCount: Int,
        val shotsCount: Int
    )

    @JsonClass(generateAdapter = true)
    data class Team(
        val id: Int,
        val name: String,
        val userName: String,
        val htmlUrl: String,
        val avatarUrl: String
    )
}
