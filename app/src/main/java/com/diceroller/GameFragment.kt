package com.diceroller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.diceroller.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class GameFragment : Fragment() {

    data class Question(val question: String, val answers: List<String>)

    lateinit var currentQues: Question

    lateinit var answerSel: MutableList<String>
    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            question = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")
        ),
        Question(
            question = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            question = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            question = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set question", "OnClick")
        ),
        Question(
            question = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")
        ),
        Question(
            question = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        Question(
            question = "Android vector format?",
            answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")
        ),
        Question(
            question = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        Question(
            question = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        ),
        Question(
            question = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )

    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false
        )


        randamonizeQuestions()
        // Bind this fragment class to the layout
        binding.gameData = this

        binding.btnSubmit.setOnClickListener() {
            var checkedId: Int = binding.radioGroup.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.radioButton2 -> answerIndex = 1
                    R.id.radioButton3 -> answerIndex = 2
                    R.id.radioButton5 -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answerSel[answerIndex] == currentQues.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQues = questions[questionIndex]
                        setQuestions()
                        binding.invalidateAll()
                        // We've won!  Navigate to the gameWonFragment.
                        Toast.makeText(context, "Correct Answer", Toast.LENGTH_SHORT).show()
                    } else {

                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    private var questionIndex: Int = 0

    private fun setQuestions() {
        currentQues = questions[questionIndex]
        answerSel = currentQues.answers.toMutableList()
        answerSel.shuffle()
    }

    private fun randamonizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestions()
    }
}
