package com.example.wordleproject1
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var chancesLeft = 3

    private lateinit var guessEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var submitButton: Button
    private lateinit var targetWordTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessEditText = findViewById(R.id.guessEditText)
        resultTextView = findViewById(R.id.resultTextView)
        submitButton = findViewById(R.id.submitButton)
        targetWordTextView = findViewById(R.id.targetWordTextView)

        submitButton.setOnClickListener {
            val guess = guessEditText.text.toString().toUpperCase()
            if (chancesLeft > 0) {
                val result = checkGuess(guess)
                resultTextView.text = result
                chancesLeft--
                if (result == "0000") {
                    submitButton.isEnabled = false
                    targetWordTextView.text = wordToGuess
                }
            }
        }
    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "0"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}

