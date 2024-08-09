package br.com.kbat.educamat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import br.com.kbat.educamat.presentation.theme.EducaMatTheme


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
                onNavigateToHome = { navController.navigate(HomeGraphRoute) }// FIXME
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

sealed class HomeNavItem(
    val label: String,
    val icon: Int,
    val route: String
) {
    data object Progress : HomeNavItem(
        label = "Progresso",
        icon = R.drawable.progress,
        route = ProgressRoute
    )

    data object Questions : HomeNavItem(
        label = "Perguntas",
        icon = R.drawable.minus,
        route = QuestionsRoute
    )

    data object Theories : HomeNavItem(
        label = "Teorias",
        icon = R.drawable.multiply,
        route = TheoriesRoute
    )

    data object Settings : HomeNavItem(
        label = "Ajustes",
        icon = R.drawable.divide,
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
        modifier = Modifier.clip(
            shape = RoundedCornerShape(
                topStartPercent = 30,
                topEndPercent = 30
            )
        ),
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
                            modifier = Modifier.height(20.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = 12.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = Color.White,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        indicatorColor = Color.Transparent,
                        selectedIconColor = Color.White,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    val navController: NavHostController = rememberNavController()
    EducaMatTheme {
        BottomBar(navController = navController)
    }
}