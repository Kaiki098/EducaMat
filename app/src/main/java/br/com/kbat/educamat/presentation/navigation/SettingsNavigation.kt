package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.settings.SettingsScreen

const val SettingsRoute = "settingsscreen"

fun NavGraphBuilder.settingsDestination(
    defaultModifier: Modifier
) {
    composable(
        route = SettingsRoute
    ) {
        SettingsScreen(defaultModifier)
    }
}