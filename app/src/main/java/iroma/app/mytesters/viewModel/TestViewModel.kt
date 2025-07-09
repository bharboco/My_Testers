package iroma.app.mytesters.viewModel

//import androidx.compose.runtime.mutableStateMapOf
//import androidx.compose.runtime.snapshots.SnapshotStateMap
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import iroma.app.mytesters.model.AnswerDao
//import iroma.app.mytesters.model.AnswerEntity
//import iroma.app.mytesters.model.TestConfig
//import kotlinx.coroutines.launch

//class TestViewModel(private val answerDao: AnswerDao) : ViewModel() {
//    private val _answers = mutableStateMapOf<Int, String>()
//    val answers: SnapshotStateMap<Int, String> = _answers
//
//    fun saveAnswer(questionId: Int, answer: String) {
//        viewModelScope.launch {
//            answerDao.insert(
//                AnswerEntity(
//                    questionId = questionId,
//                    answerType = if (questionId > testConfig.testCount) "text" else "radio",
//                    answerValue = answer
//                )
//            )
//            _answers[questionId] = answer
//        }
//    }
//
//    suspend fun loadInitialData(testConfig: TestConfig) {
//        answerDao.getByQuestionRange(1, testConfig.testCount + testConfig.advancedTaskCount)
//            .forEach { answer ->
//                _answers[answer.questionId] = answer.answerValue
//            }
//    }
//}