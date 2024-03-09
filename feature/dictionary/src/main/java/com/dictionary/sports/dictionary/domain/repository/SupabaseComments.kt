package com.dictionary.sports.dictionary.domain.repository

import com.dictionary.sports.dictionary.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface SupabaseComments {
    suspend fun getComments(filterValue: Int): Result<Flow<List<Comment>>>

    suspend fun createComment(
        commentText: String,
        commentFor: Int
    ): Result<Unit>

    suspend fun leaveChannel()
}