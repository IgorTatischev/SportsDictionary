package com.dictionary.sports.dictionary.domain.repository

import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface SupabaseComments {
    suspend fun getComments(filterValue: Int): List<Comment>

    suspend fun createComment(
        commentText: String,
        commentFor: Int
    ): CommentsState

    suspend fun checkComments(): Flow<List<Comment>>

    suspend fun subChannel()
}