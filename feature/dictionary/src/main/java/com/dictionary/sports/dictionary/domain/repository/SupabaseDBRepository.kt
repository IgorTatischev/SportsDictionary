package com.dictionary.sports.dictionary.domain.repository

import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.domain.model.Comment

interface SupabaseDBRepository {
    suspend fun getComments(filterValue: Int): List<Comment>

    suspend fun createComment(
        commentText: String,
        commentFor: Int
    ): CommentsState
}