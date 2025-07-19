package iroma.app.mytesters

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import iroma.app.mytesters.view.CheckTestsActivity
import iroma.app.mytesters.view.CreateTestActivity
import iroma.app.mytesters.ui.theme.MyTestersTheme
import iroma.app.mytesters.viewModel.TestViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        checkTests=::checkTests,
                        createTests = ::createTests
                    )
                }
            }
        }
    }
    private fun checkTests() {
        val intentCreate = Intent(this, CheckTestsActivity::class.java)
        startActivity(intentCreate)
    }

    private fun createTests() {
        val intentCreate = Intent(this, CreateTestActivity::class.java)
        startActivity(intentCreate)
    }
}

@Composable
fun Greeting(testViewModel: TestViewModel = viewModel(factory = TestViewModel.factory),name: String, modifier: Modifier = Modifier,checkTests: () -> Unit, createTests: () -> Unit) {
    val itemsList= testViewModel.itemsList.collectAsState(initial = emptyList())
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = Modifier
            .clickable { checkTests() }
            .background(Color.LightGray) ,text = "Проверить тест")
        Text(modifier = Modifier
            .clickable { createTests() }
            .background(Color.LightGray), text = "Создать тест")
    }
}