package br.com.kbat.educamat.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.kbat.educamat.presentation.ui.progress.ProgressScreen
import br.com.kbat.educamat.presentation.ui.questions.QuestionsScreen
import br.com.kbat.educamat.presentation.ui.settings.SettingsScreen
import br.com.kbat.educamat.presentation.ui.singUp.SignUpScreen
import br.com.kbat.educamat.presentation.ui.theories.TheoriesScreen

@Composable
fun EducaMatApp(modifier: Modifier = Modifier) {
    val isUserLoggedIn by remember {
        mutableStateOf(false)
    }
    val showBottomBar by remember {// na navegação para responder as perguntas, a bottomBar deve desaparecer
        mutableStateOf(true)
    }
    val navController = rememberNavController()
    val starDestination = if (isUserLoggedIn) "signupscreen" else "progressscreen"

    Scaffold(
        bottomBar = { if (showBottomBar) BottomBar(navController = navController) },
    ) { innerpadding ->

        val defaultModifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)

        NavHost(navController = navController, startDestination = starDestination) {
            signUpDestination(defaultModifier)
            progressDestination(defaultModifier)
            questionsDestination(defaultModifier)
            theoriesDestination(defaultModifier)
            settingsDestination(defaultModifier)
        }
    }
}

private fun NavGraphBuilder.settingsDestination(defaultModifier: Modifier) {
    composable(route = "settingsscreen") {
        SettingsScreen(defaultModifier)
    }
}

private fun NavGraphBuilder.theoriesDestination(defaultModifier: Modifier) {
    composable(route = "theoriesscreen") {
        TheoriesScreen(defaultModifier)
    }
}

private fun NavGraphBuilder.questionsDestination(defaultModifier: Modifier) {
    composable(route = "questionsscreen") {
        QuestionsScreen(defaultModifier)
    }
}

private fun NavGraphBuilder.progressDestination(defaultModifier: Modifier) {
    composable(route = "progressscreen") {
        ProgressScreen(defaultModifier)
    }
}

private fun NavGraphBuilder.signUpDestination(defaultModifier: Modifier) {
    composable(route = "signupscreen") { // tentar verificar depois pq a extração n
        SignUpScreen(defaultModifier)
    }
}

