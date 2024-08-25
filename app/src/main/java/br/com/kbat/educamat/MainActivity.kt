package br.com.kbat.educamat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.com.kbat.educamat.data.preferences.UserPreferences
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val userPreferences: UserPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isWorkDone = false


        installSplashScreen().setKeepOnScreenCondition { //TODO talvez tenha um modo melhor que esse
            !isWorkDone
        }
        enableEdgeToEdge()

        CoroutineScope(Dispatchers.Main).launch {
            val isUserLoggedIn = userPreferences.isUserLoggedIn.first()
            setContent {
                EducaMatTheme {
                    EducaMatApp(isUserLoggedIn = isUserLoggedIn) //FIXME rotação horizontal
                }
            }
            isWorkDone = true
        }

    }
}
