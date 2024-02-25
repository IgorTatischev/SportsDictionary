package com.dictionary.sports.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dictionary.sports.resources.R as CoreRes

@Composable
fun BoxScope.BackgroundImage(alpha: Float) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopEnd)
            .alpha(alpha),
        painter = painterResource(id = CoreRes.drawable.background_dots),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .rotate(180f)
            .align(Alignment.BottomStart)
            .alpha(alpha),
        painter = painterResource(id = CoreRes.drawable.background_dots),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}