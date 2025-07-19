package iroma.app.mytesters.model
import android.app.Application
import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// вид таблицы её название и названия столбцов
@Entity
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val questionId: Int,       // Номер вопроса
    val answerType: String,    // text или radio
    val answerValue: String    // Текст ответа или номер варианта
)

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(answer: AnswerEntity)

    @Delete
    suspend fun deleteItem(answer: AnswerEntity)

    @Query("SELECT * FROM AnswerEntity")
    fun getAllItems (): Flow<List<AnswerEntity>>
}

@Database(entities = [AnswerEntity::class], version = 1)

// создание ДБ
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: iroma.app.mytesters.model.Dao
    companion object {
        fun createDataBase(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "tests.db"
            ).build()
        }
    }
}