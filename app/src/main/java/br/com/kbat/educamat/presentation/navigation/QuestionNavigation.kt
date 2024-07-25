package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.question.QuestionScreen

const val QuestionRoute = "questionscreen"

fun NavGraphBuilder.questionDestination(
    defaultModifier: Modifier,
    onPopBackStack: () -> Unit
) {
    composable(
        QuestionRoute,
    ) { backStackEntry ->
        QuestionScreen(defaultModifier, onBackClick = { onPopBackStack() })
    }
}