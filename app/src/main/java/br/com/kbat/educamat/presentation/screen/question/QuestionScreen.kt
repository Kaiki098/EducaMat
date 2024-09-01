package br.com.kbat.educamat.presentation.screen.question

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import br.com.kbat.educamat.presentation.screen.questions.QuestionChoice
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.Green
import br.com.kbat.educamat.presentation.theme.Red
import br.com.kbat.educamat.presentation.viewmodel.QuestionViewModel
import br.com.kbat.educamat.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.time.LocalDate

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    questionViewModel: QuestionViewModel = koinViewModel(),
    userViewModel: UserViewModel = koinViewModel<UserViewModel>(),
    context: Context = koinInject()
) {// TODO Adicionar UIState
    val questions by questionViewModel.questions.collectAsState() // TODO Adicionar ícone de cronômetro talvez
    var currentQuestionNumber by remember { mutableIntStateOf(0) }
    val questionText =
        "Quanto é ${questions[currentQuestionNumber].expression}?" //TODO Da pra diminuir esse código
    val scope = rememberCoroutineScope()
    var timer by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentQuestionNumber) {
        timer = 0
        while (true) {
            delay(1000L)
            timer++
        }
    }

    val onClick: (String) -> Unit = { answerGiven ->
        scope.launch {
            questionViewModel.save(
                AnsweredQuestion(
                    options = questions[currentQuestionNumber].options,
                    correctAnswer = questions[currentQuestionNumber].correctAnswer,
                    expression = questions[currentQuestionNumber].expression,
                    answerGiven = answerGiven,
                    day = LocalDate.now(),
                    time = timer
                )
            )
            if (currentQuestionNumber < questions.size - 1) {
                currentQuestionNumber++
            } else {
                Toast.makeText(context, "Fim das questões", Toast.LENGTH_LONG).show()
                onBackClick()
            }
        }

    }

    val options = questions[currentQuestionNumber].options
    val showTimer by userViewModel.isTimerOn.collectAsState(false)

    Question(
        modifier = modifier,
        questionText = questionText,
        onClick = onClick,
        options = options,
        timer = timer,
        showTimer = showTimer ?: false,
        questionIndex = "${currentQuestionNumber + 1}/${questions.size}",
        correctAnswer = questions[currentQuestionNumber].correctAnswer
    )

}

@Composable
fun Question(
    modifier: Modifier = Modifier,
    questionText: String,
    onClick: (String) -> Unit,
    options: List<String>,
    timer: Int,
    showTimer: Boolean,
    questionIndex: String,
    correctAnswer: String
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier.padding(horizontal = 30.dp, vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(20))
                    .border(
                        width = 10.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20)
                    )
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp, horizontal = 26.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = questionIndex, fontSize = 18.sp)
                    Row {
                        if (showTimer) {
                            Text(text = "$timer", fontSize = 18.sp)
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Ícone de relógio"
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = questionText,
                        fontSize = 24.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(vertical = 60.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val optionColorStates =
                    remember(questionIndex) { mutableStateMapOf<String, Color>() }
                val optionTextStates =
                    remember(questionIndex) { mutableStateMapOf<String, String>() }
                var selectedOption by remember(questionIndex) { mutableStateOf("") }
                var isChecked by remember(questionIndex) { mutableStateOf(false) }

                options.forEach { option ->
                    val optionText = optionTextStates[option] ?: option
                    val border = if (selectedOption == option) BorderStroke(
                        width = 4.dp,
                        color = Color.Black
                    ) else BorderStroke(width = 4.dp, color = MaterialTheme.colorScheme.primary)

                    QuestionChoice(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .border(border, shape = RoundedCornerShape(20)),
                        onClick = {
                            if (!isChecked) selectedOption = option
                        },
                        text = optionText,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = optionColorStates[option]
                                ?: ButtonDefaults.buttonColors().containerColor
                        )
                    )

                }

                if (!isChecked) {
                    QuestionChoice(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            if (selectedOption == correctAnswer) {
                                optionColorStates[selectedOption] = Green
                                optionTextStates[selectedOption] = "✓ $selectedOption"
                            } else {
                                optionColorStates[selectedOption] = Red
                                optionTextStates[selectedOption] = "X $selectedOption"
                            }
                            isChecked = true
                        },
                        text = "Checar"
                    )
                } else {
                    QuestionChoice(
                        modifier = Modifier.weight(1f),
                        onClick = { onClick(selectedOption) },
                        text = "Continuar"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun QuestionScreenNightPreview() {
    EducaMatTheme {
        Question(
            modifier = Modifier.fillMaxSize(),
            questionText = "Quanto é 2 + 2?",
            onClick = { },
            options = List(4) { i -> i.toString() },
            timer = 10,
            showTimer = true,
            questionIndex = "1/10",
            correctAnswer = ""
        )
    }
}