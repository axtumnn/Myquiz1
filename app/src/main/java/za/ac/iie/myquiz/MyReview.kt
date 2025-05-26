package za.ac.iie.myquiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MyReview : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_review)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtFeedback1 = findViewById<TextView>(R.id.txtFeedback1)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)


        txtScore.text = "Your score: $score / ${questions?.size ?: 5}"

        txtFeedback1.text = if (score >= 3) {
            "Great job!"
        } else {
            "Keep practising!"
        }

        btnReview.setOnClickListener {
            val reviewText = StringBuilder()
            if (questions != null && answers != null) {
                for (i in questions.indices) {
                    reviewText.append("Q${i + 1}: ${questions[i]}\nAnswer: ${if (answers[i]) "True" else "False"}\n\n")
                }
                txtFeedback1.text = reviewText.toString()
            } else {
                txtFeedback1.text = "No review available."


                btnExit.setOnClickListener {
                    finishAffinity()
                    System.exit(0)







































                    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                        v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                        )
                        insets
                    }
                }
            }
        }
    }
}