package br.com.kbat.educamat.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.kbat.educamat.data.db.Converters
import java.time.LocalDate

@Entity
@TypeConverters(Converters::class)
data class AnsweredQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val expression: String,
    val options: List<String>,
    val correctAnswer: String,
    val answerGiven: String,
    val day: LocalDate
)