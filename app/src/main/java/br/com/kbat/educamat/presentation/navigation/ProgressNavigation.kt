package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.progress.ProgressScreen

const val ProgressRoute = "progressscreen"


fun NavGraphBuilder.progressDestination(
    defaultModifier: Modifier
) {

    composable(
        route = ProgressRoute
    ) {
        ProgressScreen(defaultModifier)
    }
}
