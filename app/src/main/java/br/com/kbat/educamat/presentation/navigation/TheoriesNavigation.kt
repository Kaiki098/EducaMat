package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.TheoriesScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val TheoriesRoute = "theoriesscreen"

fun NavGraphBuilder.theoriesDestination(
    defaultModifier: Modifier,
    onNavigateToTheory: (String) -> Unit,
) {
    composable(
        route = TheoriesRoute
    ) {
        TheoriesScreen(defaultModifier, onStudyClick = { text ->
            onNavigateToTheory(text)
        })
    }
}

fun NavHostController.navigateToTheory(text: String) {
    val encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString()).replace("+", "%20")
    navigate("$TheoryRoute?text=$encodedText")
}