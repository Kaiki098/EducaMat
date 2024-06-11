package br.com.kbat.educamat.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions


sealed class HomeNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
) {
    data object Progress : HomeNavItem(
        label = "Progresso",
        Icons.Default.Star,
        route = "progressscreen"
    )

    data object Questions : HomeNavItem(
        label = "Perguntas",
        icon = Icons.Default.Email,
        route = "questionsscreen"
    )

    data object Theories : HomeNavItem(
        label = "Teorias",
        icon = Icons.Default.Create,
        route = "theoriesscreen"
    )

    data object Settings : HomeNavItem(
        label = "Configurações",
        icon = Icons.Default.Settings,
        route = "settingsscreen"
    )
}


@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavHostController) {
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