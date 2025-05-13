package com.example.hospital2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hospital2.databinding.ActivityViewProfileBinding
import com.example.hospital2.ui.home.HomeFragment

class ViewProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewProfileBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent
        val name = intent.getStringExtra("name")
        val dob = intent.getStringExtra("dob")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val gender = intent.getStringExtra("gender")

        // Display the information
        binding.yourTextView.text = """
            Name: $name
            Date of Birth: $dob
            Phone: $phone
            Email: $email
            Gender: $gender
        """.trimIndent()

        // Set a click listener for the "Go to Home" button
        binding.goHomeButton.setOnClickListener {
            // Create an intent to go back to MainActivity and specify navigation to HomeFragment
            val intent = Intent(this, MainActivity::class.java).apply {
                // Add an extra flag or data to indicate HomeFragment should be shown
                putExtra("navigateTo", "HomeFragment")
            }
            // Start MainActivity
            startActivity(intent)
            finish() // Close the current activity
        }
    }
}
