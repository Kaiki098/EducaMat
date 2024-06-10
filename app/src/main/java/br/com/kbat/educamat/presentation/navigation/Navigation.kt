package br.com.kbat.educamat.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.kbat.educamat.presentation.ui.Config.ConfigScreen
import br.com.kbat.educamat.presentation.ui.Progress.ProgressScreen
import br.com.kbat.educamat.presentation.ui.Questions.QuestionsScreen
import br.com.kbat.educamat.presentation.ui.SingUp.SignUpScreen
import br.com.kbat.educamat.presentation.ui.Theories.TheoriesScreen

@Composable
fun EducaMatApp(modifier: Modifier = Modifier) {
    val isUserLoggedIn by remember {
        mutableStateOf(false)
    }
    val showBottomBar by remember {
        mutableStateOf(true)
    }
    val navController = rememberNavController()
    val starDestination = if (isUserLoggedIn) "signupscreen" else "progressscreen"


    Scaffold (
        bottomBar = { if (showBottomBar) BottomBar() },
    ) { innerpadding ->

        val defaultModifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)

        NavHost(navController = navController, startDestination = starDestination) {
            composable(route = "signupscreen") {
                SignUpScreen(defaultModifier)
            }
            composable(route = "progressscreen") {
                ProgressScreen(defaultModifier)
            }
            composable(route = "questionsscreen") {
                QuestionsScreen(defaultModifier)
            }
            composable(route = "theoriesscreen") {
                TheoriesScreen(defaultModifier)
            }
            composable(route = "configscreen") {
                ConfigScreen(defaultModifier)
            }
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomAppBar (
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
        }
    )
}