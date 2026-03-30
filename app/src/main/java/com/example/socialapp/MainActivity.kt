package com.example.socialapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val inputTimeOfDay: EditText = findViewById(R.id.inputTimeOfDay)
        val btnGetSuggestion: Button = findViewById(R.id.btnGetSuggestion)
        val btnReset: Button = findViewById(R.id.btnReset)
        val tvSuggestion: TextView = findViewById(R.id.tvSuggestion)

        // Set up the "Get Spark" button
        btnGetSuggestion.setOnClickListener {
            Log.d("SocialApp", "Get Spark button clicked")

            val userInput = inputTimeOfDay.text.toString().trim()

            // Check for empty input
            if (userInput.isEmpty()) {
                tvSuggestion.text = "Please enter a time of day."
                Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show()
                Log.w("SocialApp", "Empty input")
                return@setOnClickListener
            }

            // Determine the suggestion based on the input (case-insensitive)
            val suggestion = when (userInput.lowercase()) {
                "morning" -> "Send a 'Good morning' text to a family member."
                "mid-morning" -> "Reach out to a colleague with a quick 'Thank you.'"
                "afternoon" -> "Share a funny meme or interesting link with a friend."
                "afternoon snack time" -> "Send a quick 'thinking of you' message."
                "dinner" -> "Call a friend or relative for a 5‑minute catch‑up."
                "after dinner", "night" -> "Leave a thoughtful comment on a friend's post."
                else -> null
            }

            // Display the suggestion or an error message
            if (suggestion != null) {
                tvSuggestion.text = suggestion
                Toast.makeText(this, "Spark ready!", Toast.LENGTH_SHORT).show()
                Log.i("SocialApp", "Suggestion shown for '$userInput'")
            } else {
                val errorMsg = "Invalid time. Try: Morning, Mid-morning, Afternoon, Afternoon Snack Time, Dinner, After Dinner, Night."
                tvSuggestion.text = errorMsg
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                Log.w("SocialApp", "Invalid input: '$userInput'")
            }
        }

        // Set up the Reset button
        btnReset.setOnClickListener {
            Log.d("SocialApp", "Reset button clicked")
            inputTimeOfDay.text.clear()
            tvSuggestion.text = ""
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
        }

        Log.i("SocialApp", "App started")
    }
}