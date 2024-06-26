package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.ui.question.QuestionScreen

const val QuestionRoute = "questionscreen"

fun NavGraphBuilder.questionDestination(
    defaultModifier: Modifier,
    onPopBackStack: () -> Unit
) {
    composable(QuestionRoute) {
        QuestionScreen(defaultModifier, onBackClick = { onPopBackStack() })
    }
}