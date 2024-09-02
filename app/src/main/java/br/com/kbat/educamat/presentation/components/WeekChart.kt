package br.com.kbat.educamat.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.presentation.theme.BlueColorScheme
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import org.koin.compose.koinInject
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.EnumMap
import java.util.Locale


@Composable
fun WeekChart(
    modifier: Modifier = Modifier,
    dailyStatisticsUnscaled: EnumMap<DayOfWeek, Int>,
    dailyStatisticsScaled: EnumMap<DayOfWeek, Dp>,
    context: Context = koinInject()
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
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
                    barHeight = dailyStatisticsScaled[dayOfWeek] ?: 0.dp,
                    day = dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale("pt", "BR")
                    ).replaceFirstChar { char -> char.uppercase() },
                    onClick = {
                        Toast.makeText(
                            context,
                            "${dailyStatisticsUnscaled[dayOfWeek] ?: 0} segundos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }

}

@Composable
fun WeekChartBar(
    onClick: () -> Unit = {},
    barHeight: Dp,
    day: String
) {
    var targetValue by remember {
        mutableStateOf(0.dp)
    }

    val animatedHeight by animateDpAsState(
        targetValue = targetValue,
        label = "height animation",
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(key1 = barHeight) {
        targetValue = barHeight
    }

    Column(
        Modifier.background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(20.dp))
                .height(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(animatedHeight)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface,
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
            color = MaterialTheme.colorScheme.onPrimary
        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WeekChartPreview() {
    val dailyStatisticsScaled = EnumMap<DayOfWeek, Dp>(DayOfWeek::class.java).apply {
        put(DayOfWeek.MONDAY, 50.dp)
        put(DayOfWeek.TUESDAY, 60.dp)
        put(DayOfWeek.WEDNESDAY, 70.dp)
        put(DayOfWeek.THURSDAY, 80.dp)
        put(DayOfWeek.FRIDAY, 90.dp)
        put(DayOfWeek.SATURDAY, 100.dp)
        put(DayOfWeek.SUNDAY, 110.dp)
    }
    val dailyStatisticsUnscaled = EnumMap<DayOfWeek, Int>(DayOfWeek::class.java).apply {
        put(DayOfWeek.MONDAY, 50)
        put(DayOfWeek.TUESDAY, 60)
        put(DayOfWeek.WEDNESDAY, 70)
        put(DayOfWeek.THURSDAY, 80)
        put(DayOfWeek.FRIDAY, 90)
        put(DayOfWeek.SATURDAY, 100)
        put(DayOfWeek.SUNDAY, 110)
    }

    EducaMatTheme(
        colorScheme = BlueColorScheme
    ) {
        WeekChart(
            dailyStatisticsScaled = dailyStatisticsScaled,
            dailyStatisticsUnscaled = dailyStatisticsUnscaled
        )
    }
}