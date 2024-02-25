package com.dictionary.sports.dictionary.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.common.navigation.SharedScreen
import com.dictionary.sports.dictionary.data.repository.SupabaseDBRepositoryImpl
import com.dictionary.sports.dictionary.domain.repository.SupabaseDBRepository
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.CommentsScreen
import com.dictionary.sports.dictionary.presentation.screens.sport_screen.SportDescriptionScreen
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel
import com.dictionary.sports.dictionary.presentation.screens.menu_screen.MenuScreen
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object DictionaryModule {
    operator fun invoke() = module {
        factoryOf(::CommentsViewModel)

        single<SupabaseDBRepository> { SupabaseDBRepositoryImpl(supabaseClient = get()) }
    }
}


val dictionaryScreenModule = screenModule {
    register<SharedScreen.Menu> {
        MenuScreen()
    }

    register<SharedScreen.Sport> { provider ->
        SportDescriptionScreen(item = provider.item)
    }

    register<SharedScreen.Comments> { provider ->
        CommentsScreen(filterValue = provider.filterValue)
    }
}