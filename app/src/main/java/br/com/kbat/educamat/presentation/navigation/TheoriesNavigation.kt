package br.com.kbat.educamat.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.theories.TheoriesScreen

const val TheoriesRoute = "theoriesscreen"

fun NavGraphBuilder.theoriesDestination(
    defaultModifier: Modifier,
    onNavigateToTheory: () -> Unit,
    alternateDirection: (AnimatedContentTransitionScope.SlideDirection) -> Unit

) {
    composable(
        route = TheoriesRoute
    ) {
        alternateDirection(AnimatedContentTransitionScope.SlideDirection.Start)
        TheoriesScreen(defaultModifier, onStudyClick = {
            alternateDirection(AnimatedContentTransitionScope.SlideDirection.Up) // fix: Ta ficando com duas animações
            onNavigateToTheory()
        })
    }
}

fun NavHostController.navigateToTheory() {
    navigate(TheoryRoute)
}