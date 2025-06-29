package iroma.app.mytesters.Model
import android.content.Context
import com.google.gson.Gson
import java.io.File

object FileManager {
    private val gson = Gson()

    // Сохраняет любой объект в JSON-файл
    fun <T> saveToFile(context: Context, fileName: String, data: T) {
        val file = File(context.filesDir, fileName)
        val json = gson.toJson(data)
        file.writeText(json)
    }

    // Считывает JSON-файл и десериализует его в объект
    fun <T> readFromFile(context: Context, fileName: String, type: Class<T>): T? {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) return null
        val json = file.readText()
        return gson.fromJson(json, type)
    }
}