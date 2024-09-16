package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation

const val HomeGraphRoute = "homeGraph"

fun NavGraphBuilder.homeGraph(
    defaultModifier: Modifier,
    onNavigateToQuestion: () -> Unit,
    onNavigateToTheory: (String) -> Unit
) {
    navigation(startDestination = ProgressRoute, route = HomeGraphRoute) {
        progressDestination(
            defaultModifier
        )
        questionsDestination(
            defaultModifier,
            onNavigateToQuestion = {
                onNavigateToQuestion()
            }
        )
        theoriesDestination(
            defaultModifier,
            onNavigateToTheory = { text ->
                onNavigateToTheory(text)
            }
        )
        settingsDestination(
            defaultModifier
        )
    }
}

