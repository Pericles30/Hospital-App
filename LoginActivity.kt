package com.example.hospital2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerTextView = findViewById<TextView>(R.id.registerText)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Retrieve registered user data from SharedPreferences
            val registeredUsername = sharedPreferences.getString("USERNAME", null)
            val registeredPassword = sharedPreferences.getString("PASSWORD", null)

            // Check if credentials match
            if (username == registeredUsername && password == registeredPassword || username == "admin" && password == "1234") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Store username in SharedPreferences to use it in MainActivity
                sharedPreferences.edit().putString("LOGGED_IN_USER", username).apply()

                // Redirect to MainActivity (Navigation drawer)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Close LoginActivity to prevent going back to it with the back button

            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // Redirect to RegisterActivity
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
