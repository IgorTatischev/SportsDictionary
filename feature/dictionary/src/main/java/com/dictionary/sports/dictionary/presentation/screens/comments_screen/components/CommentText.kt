package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.domain.model.Comment

@Composable
internal fun CommentText(
    comment: Comment,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(40))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = .2f))
            .padding(vertical = 10.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 6.dp)
                .weight(1f),
            text = comment.commentText,
            style = MaterialTheme.typography.titleSmall
        )

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = comment.userName,
                style = MaterialTheme.typography.displaySmall
            )

            Text(
                text = comment.createdAt,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}