package br.com.kbat.educamat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.kbat.educamat.presentation.navigation.HomeGraphRoute
import br.com.kbat.educamat.presentation.navigation.ProgressRoute
import br.com.kbat.educamat.presentation.navigation.QuestionRoute
import br.com.kbat.educamat.presentation.navigation.QuestionsRoute
import br.com.kbat.educamat.presentation.navigation.SettingsRoute
import br.com.kbat.educamat.presentation.navigation.SignUpRoute
import br.com.kbat.educamat.presentation.navigation.TheoriesRoute
import br.com.kbat.educamat.presentation.navigation.homeGraph
import br.com.kbat.educamat.presentation.navigation.navigateToQuestion
import br.com.kbat.educamat.presentation.navigation.navigateToTheory
import br.com.kbat.educamat.presentation.navigation.questionDestination
import br.com.kbat.educamat.presentation.navigation.signUpDestination
import br.com.kbat.educamat.presentation.navigation.theoryDestination


@Composable
fun EducaMatApp() {
    val isUserLoggedIn by remember {
        mutableStateOf(true)
    }
    var showBottomBar by remember {// na navegação para responder as perguntas, a bottomBar deve desaparecer
        mutableStateOf(true)
    }
    val navController = rememberNavController()
    val starDestination = if (isUserLoggedIn) HomeGraphRoute else SignUpRoute

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            showBottomBar = when (destination.route) {
                QuestionRoute -> false
                else -> true
            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Scaffold(
        bottomBar = { if (showBottomBar) BottomBar(navController = navController) },
    ) { innerpadding ->

        val defaultModifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)

        NavHost(navController = navController, startDestination = starDestination) {
            signUpDestination(defaultModifier)
            homeGraph(
                defaultModifier = defaultModifier,
                onNavigateToQuestion = { navController.navigateToQuestion() },
                onNavigateToTheory = { navController.navigateToTheory() }
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

sealed class HomeNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
) {
    data object Progress : HomeNavItem(
        label = "Progresso",
        Icons.Default.Star,
        route = ProgressRoute
    )

    data object Questions : HomeNavItem(
        label = "Perguntas",
        icon = Icons.Default.Email,
        route = QuestionsRoute
    )

    data object Theories : HomeNavItem(
        label = "Teorias",
        icon = Icons.Default.Create,
        route = TheoriesRoute
    )

    data object Settings : HomeNavItem(
        label = "Configurações",
        icon = Icons.Default.Settings,
        route = SettingsRoute
    )
}


@Composable
fun BottomBar(navController: NavHostController) {
    val items = remember {
        listOf(
            HomeNavItem.Progress,
            HomeNavItem.Questions,
            HomeNavItem.Theories,
            HomeNavItem.Settings
        )
    }

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route

    BottomAppBar(
        actions = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentDestination == item.route,
                    onClick = {
                        navController.navigate(item.route, navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId)
                        })
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = item.label)
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    val navController: NavHostController = rememberNavController()
    BottomBar(navController = navController)
}