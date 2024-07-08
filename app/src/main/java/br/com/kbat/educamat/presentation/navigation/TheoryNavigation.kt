package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.theory.TheoryScreen

const val TheoryRoute = "theoryscreen"

fun NavGraphBuilder.theoryDestination(
    defaultModifier: Modifier,
    onPopBackStack: () -> Unit
) {
    composable(route = TheoryRoute) {
        TheoryScreen(defaultModifier, onBackClick = { onPopBackStack() })
    }
}