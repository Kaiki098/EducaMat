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

    fun onChangeQuestionPerRound(questions: Int) {
        viewModelScope.launch {
            userPreferences.saveQuestionPerRound(questions)
        }
    }// TODO os nomes das funções estão todos errados

    fun onChangeMaxValue(value: Int) {
        viewModelScope.launch {
            userPreferences.saveMaxValue(value)
        }
    }

    fun onChangeTheme(theme: String) {
        viewModelScope.launch {
            userPreferences.saveTheme(theme)
        }
    }

    fun onSetTimer(timer: Boolean) {
        viewModelScope.launch {
            userPreferences.setIsTimerOn(timer)
        }
    }
}