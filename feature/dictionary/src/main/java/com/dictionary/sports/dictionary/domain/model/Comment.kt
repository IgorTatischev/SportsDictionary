package com.dictionary.sports.dictionary.domain.model

internal data class Comment(
    val id: Int = 0,

    val createdAt: String,

    val userId: String,

    val userName: String,

    val commentText: String,

    val commentFor: Int
)