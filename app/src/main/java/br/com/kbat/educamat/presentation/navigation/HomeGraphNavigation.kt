package br.com.kbat.educamat.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation

const val HomeGraphRoute = "homeGraph"

fun NavGraphBuilder.homeGraph(
    defaultModifier: Modifier,
    onNavigateToQuestion: () -> Unit,
    onNavigateToTheory: () -> Unit,
    alternateDirection: (AnimatedContentTransitionScope.SlideDirection) -> Unit
) {
    navigation(startDestination = ProgressRoute, route = HomeGraphRoute) {
        progressDestination(
            defaultModifier,
            alternateDirection = alternateDirection
            //transitionDirection = transitionDirection
        )
        questionsDestination(
            defaultModifier,
            onNavigateToQuestion = {
                onNavigateToQuestion()
            },
            alternateDirection = alternateDirection
            //transitionDirection = transitionDirection
        )
        theoriesDestination(
            defaultModifier,
            onNavigateToTheory = {
                onNavigateToTheory()
            },
            alternateDirection = alternateDirection
        )
        settingsDestination(
            defaultModifier,
            alternateDirection = alternateDirection
        )
    }
}

