package br.com.kbat.educamat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kbat.educamat.data.preferences.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {
    val isUserLoggedIn: StateFlow<Boolean?> = userPreferences.isUserLoggedIn.stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500)
    )

    val userName: StateFlow<String?> = userPreferences.userName.stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed(500)
    )

    val schoolYear: StateFlow<String?> = userPreferences.schoolYear.stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed(500)
    )

    val questionsPerRound: StateFlow<Int?> = userPreferences.questionsPerRound.stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(500)
    )

    val maxValue: StateFlow<Int?> = userPreferences.maxValue.stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(500)
    )

    val theme: StateFlow<String?> = userPreferences.theme.stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed(500)
    )

    val isTimerOn: StateFlow<Boolean?> = userPreferences.isTimerOn.stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500)
    )

    fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        viewModelScope.launch {
            userPreferences.setIsUserLoggedIn(isUserLoggedIn)
        }
    }

    fun saveUserName(name: String) {
        viewModelScope.launch {
            userPreferences.saveUserName(name)
        }
    }

    fun saveSchoolYear(year: String) {
        viewModelScope.launch {
            userPreferences.saveSchoolYear(year)
        }
    }

    fun saveQuestionsPerRound(questions: Int) {
        viewModelScope.launch {
            userPreferences.saveQuestionPerRound(questions)
        }
    }

    fun saveMaxValue(value: Int) {
        viewModelScope.launch {
            userPreferences.saveMaxValue(value)
        }
    }

    fun saveTheme(theme: String) {
        viewModelScope.launch {
            userPreferences.saveTheme(theme)
        }
    }

    fun setTimer(timer: Boolean) {
        viewModelScope.launch {
            userPreferences.setIsTimerOn(timer)
        }
    }
}