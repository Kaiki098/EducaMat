package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.ui.theories.TheoriesScreen

const val TheoriesRoute = "theoriesscreen"

fun NavGraphBuilder.theoriesDestination(
    defaultModifier: Modifier,
    onNavigateToTheory: () -> Unit
) {
    composable(route = TheoriesRoute) {
        TheoriesScreen(defaultModifier, onStudyClick = { onNavigateToTheory() })
    }
}

fun NavHostController.navigateToTheory() {
    navigate(TheoryRoute)
}