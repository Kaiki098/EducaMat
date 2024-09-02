package br.com.kbat.educamat.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.components.NumberPicker
import br.com.kbat.educamat.presentation.components.OutlinedText
import br.com.kbat.educamat.presentation.theme.BlueColorScheme
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.OrangeColorScheme
import br.com.kbat.educamat.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = koinViewModel()
) {
    val userName by viewModel.userName.collectAsState(initial = "")
    val schoolYear by viewModel.schoolYear.collectAsState(initial = "")
    val questionPerRound by viewModel.questionsPerRound.collectAsState(10)
    val maxValue by viewModel.maxValue.collectAsState(100)
    val isTimerOn by viewModel.isTimerOn.collectAsState(false) // FIXME refatorar

    SettingsContent(
        modifier = modifier,
        userName = userName,
        schoolYear = schoolYear,
        questionsPerRound = questionPerRound,
        maxValue = maxValue,
        isTimerOn = isTimerOn,
        onChangeTheme = { viewModel.saveTheme(it) },
        onChangeQuestionPerRound = { viewModel.saveQuestionsPerRound(it) },
        onChangeMaxValue = { viewModel.saveMaxValue(it) },
        onSetTimer = { viewModel.setTimer(it) }
    )
}

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    userName: String?,
    schoolYear: String?,
    questionsPerRound: Int?,
    maxValue: Int?,
    isTimerOn: Boolean?,
    onChangeTheme: (String) -> Unit,
    onChangeQuestionPerRound: (Int) -> Unit,
    onChangeMaxValue: (Int) -> Unit,
    onSetTimer: (Boolean) -> Unit // FIXME UIState?
) {

    Box(
        modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText("Ajustes")

            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(10)
                    )
                    .border(
                        width = 10.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10)
                    )
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .padding(30.dp)
                ) {
                    Text("Dados pessoais: ", fontSize = 24.sp)

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Nome: $userName",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Ano escolar: $schoolYear",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }

                    Text(text = "Perguntas por rodada", fontSize = 24.sp)
                    NumberPicker(
                        min = 5,
                        max = 100,
                        size = 25,
                        default = questionsPerRound ?: 0,
                        onValueChange = {
                            onChangeQuestionPerRound(it)
                        }
                    )

                    Text(text = "Valor máximo", fontSize = 24.sp)
                    NumberPicker(
                        min = 10,
                        max = 1000,
                        size = 25,
                        default = maxValue ?: 0,
                        onValueChange = {
                            onChangeMaxValue(it)
                        }
                    )


                    Text(text = "Temporizador", fontSize = 24.sp)
                    Switch(
                        checked = isTimerOn ?: false,
                        onCheckedChange = {
                            onSetTimer(it)
                        }
                    )


                    Text(text = "Tema", fontSize = 24.sp)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(50))
                                .background(color = OrangeColorScheme.primary)
                                .size(40.dp)
                                .clickable {
                                    onChangeTheme("orange")
                                }
                        )
                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(50))
                                .background(color = BlueColorScheme.primary)
                                .size(40.dp)
                                .clickable {
                                    onChangeTheme("blue")
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SettingScreenPreview() {
    EducaMatTheme(
        colorScheme = OrangeColorScheme
    ) {
        SettingsContent(
            userName = "Kaiki",
            schoolYear = "Terceiro",
            questionsPerRound = 10,
            maxValue = 100,
            onChangeTheme = {},
            onChangeQuestionPerRound = {},
            onChangeMaxValue = {},
            isTimerOn = true,
            onSetTimer = {}
        )
    }
}