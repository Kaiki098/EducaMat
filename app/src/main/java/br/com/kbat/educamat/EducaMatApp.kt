package br.com.kbat.educamat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.kbat.educamat.presentation.components.BottomBar
import br.com.kbat.educamat.presentation.navigation.HomeGraphRoute
import br.com.kbat.educamat.presentation.navigation.QuestionRoute
import br.com.kbat.educamat.presentation.navigation.SignUpRoute
import br.com.kbat.educamat.presentation.navigation.homeGraph
import br.com.kbat.educamat.presentation.navigation.navigateToHome
import br.com.kbat.educamat.presentation.navigation.navigateToQuestion
import br.com.kbat.educamat.presentation.navigation.navigateToTheory
import br.com.kbat.educamat.presentation.navigation.questionDestination
import br.com.kbat.educamat.presentation.navigation.signUpDestination
import br.com.kbat.educamat.presentation.navigation.theoryDestination


@Composable
fun EducaMatApp(
    isUserLoggedIn: Boolean?
) {
    var showBottomBar by remember {// na navegação para responder as perguntas, a bottomBar deve desaparecer
        mutableStateOf(false)
    }
    val navController = rememberNavController()
    val starDestination = if (isUserLoggedIn == true) HomeGraphRoute else SignUpRoute

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            showBottomBar = destination.route != QuestionRoute && destination.route != SignUpRoute
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }
                )
            ) {
                BottomBar(navController = navController)
            }
        },
    ) { innerpadding ->

        val defaultModifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)

        NavHost(
            navController = navController,
            startDestination = starDestination
        ) {
            signUpDestination(
                defaultModifier,
                onNavigateToHome = {
                    navController.navigateToHome()
                }
            )

            homeGraph(
                defaultModifier = defaultModifier,
                onNavigateToQuestion = {
                    navController.navigateToQuestion()
                },
                onNavigateToTheory = {
                    navController.navigateToTheory()
                }
            )
            theoryDestination(
                defaultModifier,
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
            questionDestination(
                defaultModifier,
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
