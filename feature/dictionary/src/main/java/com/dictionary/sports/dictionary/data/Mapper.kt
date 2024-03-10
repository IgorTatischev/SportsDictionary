package com.dictionary.sports.dictionary.data

import com.dictionary.sports.dictionary.data.model.CommentEntity
import com.dictionary.sports.dictionary.domain.model.Comment

internal fun CommentEntity.toComment(): Comment {
    return Comment(
        id = id,
        createdAt = createdAt,
        userName = userName,
        commentText = commentText,
        commentFor = commentFor,
        userId = userId
    )
}

internal fun Comment.toEntity(): CommentEntity {
    return CommentEntity(
        id = id,
        createdAt = createdAt,
        userId = userId,
        userName = userName,
        commentText = commentText,
        commentFor = commentFor
    )
}