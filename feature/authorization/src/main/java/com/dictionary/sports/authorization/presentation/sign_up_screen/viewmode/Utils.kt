package com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode

import io.github.jan.supabase.exceptions.RestException

fun Exception.getMessage(): String? {
    return when (this) {
        is RestException -> this.description ?: this.error
        else -> this.message
    }
}