package iroma.app.mytesters.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestConfig(
    val testName: String = "",
    val testCount: Int = 0,
    val answerOptionsCount: Int = 3,
    val advancedTaskCount: Int = 0
) : Parcelable
