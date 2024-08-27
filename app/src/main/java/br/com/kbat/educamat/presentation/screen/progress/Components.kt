package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.utils.ColorUtil
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.EnumMap
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    question: QuestionUI,
    onDeleteQuestion: (Int) -> Unit
) { // FIXME refactor it
    var showQuestion by remember {
        mutableStateOf(false)
    }
    val swipeState = rememberSwipeToDismissBoxState(
        positionalThreshold = { fullWidth -> fullWidth / 2 }
    )

    LaunchedEffect(key1 = swipeState.currentValue) {
        if (swipeState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            onDeleteQuestion(question.number)
        }
    }
    SwipeToDismissBox(
        state = swipeState,
        backgroundContent = {
            when (swipeState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Red.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }

                }

                else -> {}
            }
        },
        enableDismissFromStartToEnd = false
    ) {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .clickable { showQuestion = !showQuestion }
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        painter = painterResource(id = question.icon),
                        contentDescription = question.iconDescription,
                        tint = if (question.icon == R.drawable.wrong_icon) Color.Red else Color.Green
                    )
                    Text(
                        text = "Q${question.number}",
                        color = Color(question.color),
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = question.preview,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 20.sp
                    )
                }
                Box {
                    Text(text = "Tempo: ${question.questionTime}", fontSize = 20.sp)
                }
            }
            AnimatedVisibility(visible = showQuestion) {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Text(text = question.description, fontSize = 20.sp)
                    Text(text = "Sua resposta: " + question.userAnswear, fontSize = 20.sp)
                    Text(text = "Resposta correta: " + question.correctAnswear, fontSize = 20.sp)
                }
            }
        }
    }
}

data class QuestionUI(
    val icon: Int,
    val iconDescription: String,
    val number: Int,
    val color: Int,
    val preview: String,
    val questionTime: Int,
    val description: String,
    val userAnswear: String,
    val correctAnswear: String,
    val day: LocalDate
)// FIXME UIState

@Preview(showBackground = true)
@Composable
private fun QuestionItemPreview() {
    EducaMatTheme {
        QuestionItem(
            Modifier,
            question = QuestionUI(
                icon = R.drawable.wrong_icon,
                iconDescription = "Ícone de questão correta",
                number = 1,
                color = ColorUtil.getRandomColor(),
                preview = "4+2 é...",
                questionTime = 20,
                description = "Qual é a soma de 4 + 2?",
                userAnswear = "8",
                correctAnswear = "6",
                day = LocalDate.now()
            )
        ) {}
    }
}

@Composable
fun WeekChart(
    modifier: Modifier = Modifier,
    dailyStatistics: EnumMap<DayOfWeek, Dp>
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .background(MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(20.dp))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DayOfWeek.entries.forEach { dayOfWeek ->
                WeekChartBar(
                    barHeight = dailyStatistics[dayOfWeek] ?: 0.dp,
                    day = dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale("pt", "BR")
                    ).replaceFirstChar { char -> char.uppercase() }
                )
            }
        }
    }

}

@Composable
fun WeekChartBar(barHeight: Dp, day: String) {
    var targetValue by remember {
        mutableStateOf(0.dp)
    }

    val animatedHeight by animateDpAsState(
        targetValue = targetValue,
        label = "height",
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(key1 = barHeight) {
        targetValue = barHeight
    }

    Column(
        Modifier.background(color = MaterialTheme.colorScheme.onSurface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .height(100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(animatedHeight)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = -45f
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                },
            text = day,
            fontSize = 10.sp,
            color = Color(0xFF666666)
        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WeekChartPreview() {
    val dailyStatistics = EnumMap<DayOfWeek, Dp>(DayOfWeek::class.java).apply {
        put(DayOfWeek.MONDAY, 50.dp)
        put(DayOfWeek.TUESDAY, 60.dp)
        put(DayOfWeek.WEDNESDAY, 70.dp)
        put(DayOfWeek.THURSDAY, 80.dp)
        put(DayOfWeek.FRIDAY, 90.dp)
        put(DayOfWeek.SATURDAY, 100.dp)
        put(DayOfWeek.SUNDAY, 110.dp)
    }
    EducaMatTheme {
        WeekChart(dailyStatistics = dailyStatistics)
    }
}