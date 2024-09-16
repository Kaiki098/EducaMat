package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.TheoryScreen

const val TheoryRoute = "theoryscreen"

fun NavGraphBuilder.theoryDestination(
    defaultModifier: Modifier,
    onPopBackStack: () -> Unit
) {
    composable(route = "$TheoryRoute?text={text}") { backStackEntry ->
        val text = backStackEntry.arguments?.getString("text") ?: ""
        TheoryScreen(defaultModifier, text = text, onBackClick = { onPopBackStack() })
    }
}