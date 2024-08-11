package br.com.kbat.educamat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.kbat.educamat.data.db.dao.AnsweredQuestionDAO
import br.com.kbat.educamat.data.db.entities.AnsweredQuestionEntity

@Database(entities = [AnsweredQuestionEntity::class], version = 3)
abstract class EducaMatDatabase : RoomDatabase() {
    abstract fun answeredQuestionDao(): AnsweredQuestionDAO
}