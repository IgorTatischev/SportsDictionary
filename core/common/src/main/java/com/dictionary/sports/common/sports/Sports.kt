package com.dictionary.sports.common.sports

import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.dictionary.sports.resources.R as CoreR

enum class Sports(
    val filterValue: Int,
    val color: Color,
    @DrawableRes val iconResId: Int,
    @StringRes val nameResId: Int,
    @DrawableRes val backgroundResId: Int,
    @ArrayRes val termsResId: Int,
) {
    Football(
        filterValue = 1,
        color = Color(0xFFFAAD39),
        iconResId = CoreR.drawable.icon_football,
        nameResId = CoreR.string.football,
        backgroundResId = CoreR.drawable.football,
        termsResId = CoreR.array.football_steps
    ),
    Tennis(
        filterValue = 2,
        color = Color(0xFFCD5B88),
        iconResId = CoreR.drawable.icon_tennis,
        nameResId = CoreR.string.tennis,
        backgroundResId = CoreR.drawable.tennis,
        termsResId = CoreR.array.tennis_steps
    ),
    Basketball(
        filterValue = 3,
        color = Color(0xFF57C081),
        iconResId = CoreR.drawable.icon_basketball,
        nameResId = CoreR.string.basketball,
        backgroundResId = CoreR.drawable.basketball,
        termsResId = CoreR.array.basketball_steps
    ),
    Handball(
        filterValue = 4,
        color = Color(0xFF1D88FB),
        iconResId = CoreR.drawable.icon_handball,
        nameResId = CoreR.string.handball,
        backgroundResId = CoreR.drawable.handball,
        termsResId = CoreR.array.handball_steps
    ),
    Golf(
        filterValue = 5,
        color = Color(0xFFD05353),
        iconResId = CoreR.drawable.icon_golf,
        nameResId = CoreR.string.golf,
        backgroundResId = CoreR.drawable.golf,
        termsResId = CoreR.array.golf_steps
    ),
    Kickboxing(
        filterValue = 6,
        color = Color(0xFFE0D35E),
        iconResId = CoreR.drawable.icon_box,
        nameResId = CoreR.string.kick,
        backgroundResId = CoreR.drawable.kickboxing,
        termsResId = CoreR.array.kick_steps
    ),
    Wrestling(
        filterValue = 7,
        color = Color(0xFF57C081),
        iconResId = CoreR.drawable.icon_wrestling,
        nameResId = CoreR.string.wrestling,
        backgroundResId = CoreR.drawable.wrestling,
        termsResId = CoreR.array.wrestling_steps
    ),
    Racing(
        filterValue = 8,
        color = Color(0xFFCD5B88),
        iconResId = CoreR.drawable.icon_race,
        nameResId = CoreR.string.racing,
        backgroundResId = CoreR.drawable.racing,
        termsResId = CoreR.array.racing_steps
    ),
}

