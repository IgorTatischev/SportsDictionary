package com.dictionary.sports.settings.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileEntity(
    val id: Int = 0,
    @SerialName("name")
    val name: String,
)