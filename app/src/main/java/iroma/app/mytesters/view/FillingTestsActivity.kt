package iroma.app.mytesters.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import iroma.app.mytesters.model.TestConfig
import iroma.app.mytesters.view.ui.theme.MyTestersTheme
import androidx.compose.runtime.remember
//import androidx.room.Room
//import iroma.app.mytesters.model.AppDatabase
//import iroma.app.mytesters.viewModel.TestViewModel

class FillingTestActivity : ComponentActivity() {
    private lateinit var testConfig: TestConfig
//    private lateinit var db: AppDatabase
//    private lateinit var viewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "test-db"
//        ).build()
//
//        viewModel = TestViewModel(db.answerDao())

        testConfig = intent.getParcelableExtra("TEST_CONFIG_KEY") ?: TestConfig()
        enableEdgeToEdge()
        setContent {
            MyTestersTheme {
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
    val textAnswers = remember { mutableStateMapOf<Int, String>() }

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        NameSection("Название теста", testConfig.testName)
        TestSection(countTest = testConfig.testCount, countOptions = testConfig.answerOptionsCount)
        TaskSection(taskCount = testConfig.advancedTaskCount, onTaskChange = {answers -> textAnswers.putAll(answers)})
    }
}

@Composable
private fun NameSection(nameTest: String, value: String){
    SectionBox{
        Text(
            modifier = Modifier.background(Color.LightGray),
            text = "$nameTest: $value"
        )
    }
}

@Composable
private fun TestSection(countTest: Int,countOptions: Int){
    val selectedAnswers = remember { mutableStateMapOf<Int, Int?>() }

    SectionBox {
        for (n in 1..countTest){
            Text(
                text = "Задание №$n",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            CreateOptionsCount(
                count = countOptions,
                selectedOption = selectedAnswers[n],
                onOptionSelected = { selectedAnswer ->
                    selectedAnswers[n] = selectedAnswer
                }
            )
        }
    }
}

@Composable
private fun CreateOptionsCount(count:Int,selectedOption: Int?, onOptionSelected: (Int) -> Unit) {
    Column {
        Text(
            text = "Выберите правильный ответ:",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        for (option in 1..count) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option)},
                    modifier = Modifier.size(24.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Blue,
                        unselectedColor = Color.Gray
                        )
                )
                Text(
                    text = "Вариант $option",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun TaskSection(taskCount : Int, onTaskChange: (Map<Int, String>) -> Unit){
    val answers = remember {
        mutableStateMapOf<Int, String>()
    }

    SectionBox {
        Column {
            for (taskNumber in 1..taskCount)
                InputField(
                    value = answers[taskNumber] ?: "",
                    onValueChange = { newValue ->
                        answers[taskNumber] = newValue
                    },
                    label = "Ответ на задание №$taskNumber",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
        }
    }
}


@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        singleLine = true
    )
}