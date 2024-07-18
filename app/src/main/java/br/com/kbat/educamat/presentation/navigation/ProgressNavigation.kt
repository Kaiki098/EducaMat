package br.com.kbat.educamat.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.progress.ProgressScreen

const val ProgressRoute = "progressscreen"


fun NavGraphBuilder.progressDestination(
    defaultModifier: Modifier,
    alternateDirection: (AnimatedContentTransitionScope.SlideDirection) -> Unit
    //   transitionDirection: AnimatedContentTransitionScope.SlideDirection
) {

    composable(
        route = ProgressRoute
    ) {
        alternateDirection(AnimatedContentTransitionScope.SlideDirection.Start)
        ProgressScreen(defaultModifier)
    }
}
