package br.com.kbat.educamat.presentation.screen.question

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import br.com.kbat.educamat.presentation.theme.Orange
import br.com.kbat.educamat.presentation.viewmodel.QuestionViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.time.LocalDate

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    questionViewModel: QuestionViewModel = koinViewModel(),
    context: Context = koinInject()
) {// TODO Adicionar UIState
    val questions by questionViewModel.questions.collectAsState() // TODO Adicionar funcionalidade de cronômetro talvez
    var currentQuestionNumber by remember {
        mutableIntStateOf(0)
    }
    val questionText =
        "Quanto é ${questions[currentQuestionNumber].expression}?" //TODO Da pra diminuir esse código

    val scope = rememberCoroutineScope()
    val onClick: (String) -> Unit = { answerGiven ->
        if (currentQuestionNumber < questions.size - 1) {
            scope.launch {
                questionViewModel.save(
                    AnsweredQuestion(
                        options = questions[currentQuestionNumber].options,
                        correctAnswer = questions[currentQuestionNumber].correctAnswer,
                        expression = questions[currentQuestionNumber].expression,
                        answerGiven = answerGiven,
                        day = LocalDate.now()
                    )
                )
                currentQuestionNumber++
                Toast.makeText(context, "Resposta dada: $answerGiven", Toast.LENGTH_SHORT).show()
            }
        } else {
            scope.launch {
                questionViewModel.save(
                    AnsweredQuestion(
                        options = questions[currentQuestionNumber].options,
                        correctAnswer = questions[currentQuestionNumber].correctAnswer,
                        expression = questions[currentQuestionNumber].expression,
                        answerGiven = answerGiven,
                        day = LocalDate.now()
                    )
                )
                Toast.makeText(context, "Fim das questões", Toast.LENGTH_LONG).show()
                onBackClick()
            }
        }
    }

    val optionText = questions[currentQuestionNumber].options

    Question(
        modifier = modifier,
        questionText = questionText,
        onClick = onClick,
        optionText = optionText
    )
}

@Composable
fun Question(
    modifier: Modifier = Modifier,
    questionText: String,
    onClick: (String) -> Unit,
    optionText: List<String>
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
                    .fillMaxWidth()
                    .weight(0.3f)
                    .border(width = 10.dp, color = Orange, shape = RoundedCornerShape(20))
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = questionText,
                    fontSize = 24.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(vertical = 60.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                List(4) { i ->
                    QuestionChoice(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onClick = { onClick(optionText[i]) },
                        text = optionText[i]
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuestionScreenPreview() {
    EducaMatTheme {
        Question(
            modifier = Modifier.fillMaxSize(),
            questionText = "Quanto é 2 + 2?",
            onClick = { },
            optionText = List(4) { i -> i.toString() }
        )
    }
}