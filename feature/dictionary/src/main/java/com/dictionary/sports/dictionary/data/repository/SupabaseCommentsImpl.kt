package com.dictionary.sports.dictionary.data.repository

import com.dictionary.sports.dictionary.data.model.CommentEntity
import com.dictionary.sports.dictionary.data.toComment
import com.dictionary.sports.dictionary.data.toEntity
import com.dictionary.sports.dictionary.domain.model.Comment
import com.dictionary.sports.dictionary.domain.repository.SupabaseComments
import com.dictionary.sports.supabase.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(SupabaseExperimental::class)
internal class SupabaseCommentsImpl(
    private val client: SupabaseClient,
    private val supabaseRepository: SupabaseRepository,
) : SupabaseComments {

    private val channel = client.channel("comments")

    override suspend fun getComments(filterValue: Int): Result<Flow<List<Comment>>> = runCatching {
        val data = channel.postgresListDataFlow(
            schema = "public",
            table = "comments",
            primaryKey = CommentEntity::id,
            filter = FilterOperation(
                column = "comment_for",
                operator = FilterOperator.EQ,
                value = filterValue
            ),
        ).flowOn(Dispatchers.IO)

        channel.subscribe()

        val flow = data.map { list ->
            list.map { comment ->
                comment.toComment()
            }.reversed()
        }

        return Result.success(flow)
    }

    override suspend fun createComment(
        commentText: String,
        commentFor: Int,
    ): Result<Unit> = runCatching {

        val sdf = SimpleDateFormat("d MMM HH:mm", Locale.getDefault())
        val currentDate = sdf.format(Date())
        val uuid = client.auth.retrieveUserForCurrentSession().id
        val name = supabaseRepository.getCurrentUserName()

        val comment = Comment(
            createdAt = currentDate.toString(),
            userId = uuid,
            userName = name,
            commentText = commentText,
            commentFor = commentFor
        )

        client.postgrest
            .from("comments")
            .insert(comment.toEntity())
    }

    override suspend fun leaveChannel() {
        channel.unsubscribe()
        client.realtime.removeChannel(channel)
    }

}