package com.dictionary.sports.common.supabase.state

sealed class ChangeUserDataState {
    object Success: ChangeUserDataState()

    object Error: ChangeUserDataState()
}