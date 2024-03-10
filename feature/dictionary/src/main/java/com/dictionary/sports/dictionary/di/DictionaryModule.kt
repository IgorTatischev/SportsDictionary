package com.dictionary.sports.dictionary.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.common.navigation.SharedScreen
import com.dictionary.sports.dictionary.data.repository.SupabaseCommentsImpl
import com.dictionary.sports.dictionary.domain.repository.SupabaseComments
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.CommentsScreen
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.CommentsScreenModel
import com.dictionary.sports.dictionary.presentation.screens.menu_screen.MenuScreen
import com.dictionary.sports.dictionary.presentation.screens.sport_screen.SportDescriptionScreen
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DictionaryModule {
    operator fun invoke() = module {
        factoryOf(::CommentsScreenModel)

        singleOf(::SupabaseCommentsImpl) bind SupabaseComments::class
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