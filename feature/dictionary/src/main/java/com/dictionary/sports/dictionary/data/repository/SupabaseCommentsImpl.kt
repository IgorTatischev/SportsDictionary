package com.dictionary.sports.dictionary.data.repository

import com.dictionary.sports.dictionary.data.model.CommentEntity
import com.dictionary.sports.dictionary.data.toEntity
import com.dictionary.sports.dictionary.data.toComment
import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.domain.model.Comment
import com.dictionary.sports.dictionary.domain.repository.SupabaseComments
import com.dictionary.sports.resources.R
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(SupabaseExperimental::class)
class SupabaseCommentsImpl(
    private val client: SupabaseClient
) : SupabaseComments {

    private val channel = client.channel("comments")

    override suspend fun checkComments(): Flow<List<Comment>> = channel
        .postgresListDataFlow(schema = "public", table = "comments", primaryKey = Comment::id)
        .flowOn(Dispatchers.IO)

    override suspend fun subChannel() {
        channel.subscribe()
    }

    override suspend fun getComments(filterValue: Int): List<Comment> {
        return client.postgrest
            .from("comments")
            .select(
                //todo filter
//                filter = {
//                    isIn("comment_for", listOf(filterValue))
//                    order(
//                        column = "id",
//                        order = Order.DESCENDING
//                    )
//                }
            )
            .decodeList<CommentEntity>()
            .map { it.toComment() }
    }

    override suspend fun createComment(
        commentText: String,
        commentFor: Int
    ): CommentsState {
        try {
            val sdf = SimpleDateFormat("d MMM HH:mm", Locale.getDefault())
            val currentDate = sdf.format(Date())

            val currentUser = client.auth.retrieveUserForCurrentSession()

            val userEmail = currentUser.email ?: ""
            var name = userEmail.substringBefore('@')
            val uuid = currentUser.id

            currentUser.userMetadata?.let {
                val jsonObject = JSONObject(it.toString())
                name = jsonObject.getString("name")
            }

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
            return CommentsState.Success

        } catch (e: Exception) {
            return CommentsState.Error(R.string.error)
        }
    }
}