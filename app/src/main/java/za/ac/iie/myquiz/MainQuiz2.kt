package za.ac.iie.myquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class MainQuiz2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val questions = arrayOf(
        "Nelson Mandela was president in 1994",
        "The capital of South Africa is Johannesburg",
        "South Africa won the 2010 World Cup",
        "Freedom Day is celebrated on the 27th April",
        "Apartheid ended in 1994"
    )

    private val answers = booleanArrayOf(
        true,      // Mandela
        false,     // Capital is Pretoria
        false,     // Spain won
        true,      // 27 April
        true       // 1994
    )

    private var currentQuestion = 0
    private var score = 0
    private var hasAnswered = false

    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_quiz2)

        txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        txtFeedback = findViewById<TextView>(R.id.txtFeedback)
        btnTrue = findViewById<Button>(R.id.btnTrue)
        btnFalse = findViewById<Button>(R.id.btnFalse)
        btnNext = findViewById<Button>(R.id.btnNext)

        showQuestion()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnNext.setOnClickListener {
            if (!hasAnswered) {
                Toast.makeText(this, "Please select True or False", Toast.LENGTH_SHORT).show()
            } else {
                currentQuestion++
                if (currentQuestion < questions.size) {
                    showQuestion()
                } else {
                    // Quiz finished
                    val intent = Intent(this, MyReview::class.java)
                    intent.putExtra("score", score)
                    intent.putExtra("questions", questions)
                    intent.putExtra("answers", answers)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showQuestion() {
        txtQuestion.text = questions[currentQuestion]
        txtFeedback.text = ""
        hasAnswered = false
    }

    private fun checkAnswer(userAnswer: Boolean) {
        hasAnswered = true
        val correctAnswer = answers[currentQuestion]
        if (userAnswer == correctAnswer) {
            txtFeedback.text = "Correct!"
            score++
        } else {
            txtFeedback.text = "Incorrect!"
        }




























        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}