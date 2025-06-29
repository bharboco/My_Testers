package iroma.app.mytesters.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import iroma.app.mytesters.model.TestConfig
import iroma.app.mytesters.view.ui.theme.MyTestersTheme

class FillingTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestersTheme {
                val testConfig = remember {
                    intent.getParcelableExtra("TEST_CONFIG_KEY") ?: TestConfig()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckTest(
                        modifier = Modifier.padding(innerPadding),
                        testConfig = testConfig
                    )
                }
            }
        }
    }
}

@Composable
fun CheckTest(modifier: Modifier = Modifier, testConfig: TestConfig) {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = Modifier
            .background(Color.LightGray) ,text = "Название теста: ${testConfig.testName}")
        Text(modifier = Modifier
            .background(Color.LightGray), text = "Количество тестов: ${testConfig.testCount}")
    }
}