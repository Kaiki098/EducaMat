package br.com.kbat.educamat.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.settings.SettingsScreen

const val SettingsRoute = "settingsscreen"

fun NavGraphBuilder.settingsDestination(
    defaultModifier: Modifier,
    alternateDirection: (AnimatedContentTransitionScope.SlideDirection) -> Unit
) {
    composable(
        route = SettingsRoute
    ) {
        alternateDirection(AnimatedContentTransitionScope.SlideDirection.End)
        SettingsScreen(defaultModifier)
    }
}