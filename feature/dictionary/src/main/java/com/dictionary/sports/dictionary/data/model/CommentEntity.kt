package com.dictionary.sports.dictionary.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CommentEntity(

    val id: Int = 0,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("user_id")
    val userId : String,

    @SerialName("user_name")
    val userName: String,

    @SerialName("comment_text")
    val commentText: String,

    @SerialName("comment_for")
    val commentFor: Int
)