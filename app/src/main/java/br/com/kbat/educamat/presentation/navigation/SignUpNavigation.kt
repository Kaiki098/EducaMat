package br.com.kbat.educamat.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.kbat.educamat.presentation.screen.singUp.SignUpScreen

const val SignUpRoute = "signupscreen"

fun NavGraphBuilder.signUpDestination(
    defaultModifier: Modifier,
    onNavigateToHome: () -> Unit
) {
    composable(route = SignUpRoute) {
        SignUpScreen(defaultModifier, onNavigateToHome)
    }
}
