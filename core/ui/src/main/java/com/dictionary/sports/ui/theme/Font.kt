package com.dictionary.sports.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.dictionary.sports.resources.R as CoreRes

val mainFont = FontFamily(
    listOf(
        Font(resId = CoreRes.font.poppins_regular, weight = FontWeight.Normal),
        Font(resId = CoreRes.font.poppins_semibold, weight = FontWeight.SemiBold)
    )
)