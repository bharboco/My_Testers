package iroma.app.mytesters.model
//import androidx.room.Dao
//import androidx.room.Database
//import androidx.room.Entity
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.PrimaryKey
//import androidx.room.Query
//import androidx.room.RoomDatabase

//@Entity(tableName = "answers")
//data class AnswerEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val questionId: Int,       // Номер вопроса
//    val answerType: String,    // text или radio
//    val answerValue: String    // Текст ответа или номер варианта
//)
//
//@Dao
//interface AnswerDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(answer: AnswerEntity)
//
//    @Query("SELECT * FROM answers WHERE questionId = :questionId")
//    suspend fun getByQuestion(questionId: Int): AnswerEntity?
//
//    @Query("DELETE FROM answers")
//    suspend fun clearAll()
//}
//
//@Database(entities = [AnswerEntity::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun answerDao(): AnswerDao
//}