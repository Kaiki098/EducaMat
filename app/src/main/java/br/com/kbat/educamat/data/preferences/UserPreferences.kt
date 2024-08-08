package br.com.kbat.educamat.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
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
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val SCHOOL_YEAR_KEY = stringPreferencesKey("school_year")
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

    suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = isUserLoggedIn
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
}