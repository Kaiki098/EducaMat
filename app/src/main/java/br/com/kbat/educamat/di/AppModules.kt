package br.com.kbat.educamat.di

import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.presentation.viewmodel.QuestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::QuestionRepository)
    viewModelOf(::QuestionViewModel)
}