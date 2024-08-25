package br.com.kbat.educamat.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.kbat.educamat.data.db.entities.AnsweredQuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnsweredQuestionDAO {

    @Query("SELECT * FROM AnsweredQuestionEntity")
    fun getAll(): Flow<List<AnsweredQuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(question: AnsweredQuestionEntity)

    @Query("DELETE FROM AnsweredQuestionEntity WHERE id = :id")
    suspend fun deleteById(id: Int)
}