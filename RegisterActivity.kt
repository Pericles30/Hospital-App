package com.example.hospital2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val username = findViewById<EditText>(R.id.usernameEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val backToLoginButton = findViewById<Button>(R.id.backToLoginButton)

        registerButton.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                // Save user credentials in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("USERNAME", user)
                editor.putString("PASSWORD", pass)
                editor.apply()

                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                // Redirect to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Close registration screen
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        backToLoginButton.setOnClickListener {
            // Redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close registration screen
        }
    }
}