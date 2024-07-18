package br.com.kbat.educamat.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.questions.QuestionsScreen

const val QuestionsRoute = "questionsscreen"

fun NavGraphBuilder.questionsDestination(
    defaultModifier: Modifier,
    onNavigateToQuestion: () -> Unit,
    alternateDirection: (AnimatedContentTransitionScope.SlideDirection) -> Unit
    // transitionDirection: AnimatedContentTransitionScope.SlideDirection
) {
    composable(
        route = QuestionsRoute
    ) {
        alternateDirection(AnimatedContentTransitionScope.SlideDirection.Start)
        QuestionsScreen(defaultModifier, onStartCLick = {
            alternateDirection(AnimatedContentTransitionScope.SlideDirection.Up)// fix: Ta ficando com duas animações
            onNavigateToQuestion()
        })
    }
}

fun NavHostController.navigateToQuestion() {
    navigate(QuestionRoute)
}

