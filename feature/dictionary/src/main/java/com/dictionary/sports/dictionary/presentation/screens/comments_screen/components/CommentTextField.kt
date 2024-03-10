package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dictionary.sports.dictionary.R

@Composable
internal fun CommentTextField(
    value: String,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        modifier = Modifier.padding(top = 20.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(50))
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.onSecondary,
                                MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    )
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()

                if (value.isEmpty())
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = stringResource(id = R.string.comment_label),
                        style = MaterialTheme.typography.displaySmall
                    )
            }
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = MaterialTheme.typography.titleSmall.copy(fontSize = 16.sp)
    )
}