package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.QuestionsScreen

const val QuestionsRoute = "questionsscreen"

fun NavGraphBuilder.questionsDestination(
    defaultModifier: Modifier,
    onNavigateToQuestion: () -> Unit
) {
    composable(
        route = QuestionsRoute
    ) {
        QuestionsScreen(defaultModifier, onNavigateToQuestionClick = {
            onNavigateToQuestion()
        })
    }
}

fun NavHostController.navigateToQuestion() {
    navigate(QuestionRoute)
}

