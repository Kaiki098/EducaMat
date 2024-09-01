package br.com.kbat.educamat.data.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
        val IS_TIMER_ON = booleanPreferencesKey("is_timer_on")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val SCHOOL_YEAR_KEY = stringPreferencesKey("school_year")
        val THEME_KEY = stringPreferencesKey("theme")
        val QUESTIONS_PER_ROUND = intPreferencesKey("questions_per_round")
        val MAX_VALUE = intPreferencesKey("max_value")
    }

    val isUserLoggedIn: Flow<Boolean?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[IS_USER_LOGGED_IN]
        }

    val userName: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USER_NAME_KEY]
        }

    val schoolYear: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[SCHOOL_YEAR_KEY]
        }

    val theme: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[THEME_KEY]
        }


    val questionsPerRound: Flow<Int?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[QUESTIONS_PER_ROUND]
        }

    val maxValue: Flow<Int?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[MAX_VALUE]
        }

    val isTimerOn: Flow<Boolean?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[IS_TIMER_ON]
        }


    suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = isUserLoggedIn
        }
    }

    suspend fun setIsTimerOn(isTimerOn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_TIMER_ON] = isTimerOn
            Log.d("USER PREFERENCES", "Timer saved ${preferences[IS_TIMER_ON]}")
        }
    }

    suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    suspend fun saveSchoolYear(year: String) {
        dataStore.edit { preferences ->
            preferences[SCHOOL_YEAR_KEY] = year
        }
    }

    suspend fun saveTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    suspend fun saveQuestionPerRound(numberOfQuestions: Int) {
        dataStore.edit { preferences ->
            preferences[QUESTIONS_PER_ROUND] = numberOfQuestions
        }
    }

    suspend fun saveMaxValue(maxValue: Int) {
        dataStore.edit { preferences ->
            preferences[MAX_VALUE] = maxValue
        }
    }
}