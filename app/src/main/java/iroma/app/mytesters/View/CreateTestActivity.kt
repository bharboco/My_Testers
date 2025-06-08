package iroma.app.mytesters.View

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iroma.app.mytesters.ui.theme.MyTestersTheme

class CreateTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateGreeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun CreateGreeting(name: String, modifier: Modifier = Modifier) {
    var testName by remember { mutableStateOf("") }
    var testCount by remember { mutableStateOf("") }
    var advancedTaskCount by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("3") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopSection(testName, testCount, onTestNameChange = { testName = it }, onTestCountChange = {testCount = it})
        MiddleSection(
            selectedOption,
            onOptionSelected = { selectedOption = it }
        )
        BottomSection(advancedTaskCount, onAdvancedTaskCountChange = { advancedTaskCount = it })

        Spacer(modifier = Modifier.weight(1f))

        ContinueButton(onClick = {
            // Здесь логика продолжения
            // перейти на другой экран
            // создать oblect class и сохранить данные, pattern repository ( изучить )
            // на новом экране после заполнения данных записать в json файл
            // логика во view model, паттерн MVVM, архитектурный паттерн (presentetion, data, domen)
        })
    }
}

@Composable
private fun TopSection(
    testName: String,
    testCount: String,
    onTestNameChange: (String) -> Unit,
    onTestCountChange: (String) -> Unit
) {
    SectionBox {
        GreetingItem(text = "Название теста")
        InputField(
            value = testName,
            onValueChange = onTestNameChange,
            label = "Введите название теста"
        )

        GreetingItem(text = "Количество тестов с вариантами ответа")
        InputField(
            value = testCount,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("\\d+"))) {
                    onTestCountChange(newValue)
                }
            },
            label = "Введите количество тестов",
            keyboardType = KeyboardType.Number
        )
    }
}

@Composable
private fun MiddleSection(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    SectionBox {
        GreetingItem(text = "Количество вариантов ответа в тестах")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButtonOption(
                    text = "3",
                    selected = selectedOption == "3",
                    onClick = { onOptionSelected("3") }
                )
                RadioButtonOption(
                    text = "4",
                    selected = selectedOption == "4",
                    onClick = { onOptionSelected("4") }
                )
            }
        }
    }
}

@Composable
private fun BottomSection(
    advancedTaskCount: String,
    onAdvancedTaskCountChange: (String) -> Unit
) {
    SectionBox {
        GreetingItem(text = "Количество заданий повышенной сложности")
        InputField(
            value = advancedTaskCount,
            onValueChange = {newValue ->
                if(newValue.isEmpty() || newValue.matches(Regex("\\d+"))){onAdvancedTaskCountChange(newValue)}},
            label = "Введите количество",
            keyboardType = KeyboardType.Number
        )
    }
}

@Composable
private fun ContinueButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 8.dp)
            .background (Color.Green)
    ) {
        Text(
            text = "Продолжить",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun GreetingItem(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun RadioButtonOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CustomRadioButton(selected = selected, onClick = onClick)
        Text(text = text)
    }
}

@Composable
private fun SectionBox(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            content()
        }
    }
}

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(
                color = if (selected) Color.Blue else Color.LightGray,
                shape = MaterialTheme.shapes.small
            )
            .border(width = 2.dp, color = Color.Gray)
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.small)
            )
        }
    }
}