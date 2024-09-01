package br.com.kbat.educamat.di

import androidx.room.Room
import br.com.kbat.educamat.data.EducaMatDatabase
import br.com.kbat.educamat.data.preferences.UserPreferences
import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.presentation.screen.progress.ProgressViewModel
import br.com.kbat.educamat.presentation.viewmodel.QuestionViewModel
import br.com.kbat.educamat.presentation.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::QuestionRepository)
    viewModelOf(::QuestionViewModel)
    viewModelOf(::ProgressViewModel)
    viewModelOf(::UserViewModel)
}

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            EducaMatDatabase::class.java, "answered-questions.db"
        ).build()
    }
    single {
        get<EducaMatDatabase>().answeredQuestionDao()
    }
    single { UserPreferences(androidContext()) }
}