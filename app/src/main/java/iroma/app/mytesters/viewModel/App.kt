package iroma.app.mytesters.viewModel

import android.app.Application
import iroma.app.mytesters.model.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.createDataBase(this) }
}