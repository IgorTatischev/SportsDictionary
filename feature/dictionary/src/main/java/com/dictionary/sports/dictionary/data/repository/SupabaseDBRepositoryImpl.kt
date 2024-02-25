package com.dictionary.sports.dictionary.data.repository

import android.util.Log
import com.dictionary.sports.common.supabase.SupabaseClient
import com.dictionary.sports.dictionary.data.model.CommentEntity
import com.dictionary.sports.dictionary.data.toEntity
import com.dictionary.sports.dictionary.data.toComment
import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.domain.model.Comment
import com.dictionary.sports.dictionary.domain.repository.SupabaseDBRepository
import com.dictionary.sports.resources.R
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SupabaseDBRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : SupabaseDBRepository {

    override suspend fun getComments(filterValue: Int): List<Comment> {
        return supabaseClient.client.postgrest
            .from("comments")
            .select(
                filter = {
                    isIn("comment_for", listOf(filterValue))
                    order(
                        column = "id",
                        order = Order.DESCENDING
                    )
                }
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

            val currentUser = supabaseClient.client.gotrue.retrieveUserForCurrentSession()

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

            supabaseClient.client.postgrest
                .from("comments")
                .insert(comment.toEntity())
            return CommentsState.Success

        } catch (e: Exception) {
            return CommentsState.Error(R.string.error)
        }
    }
}