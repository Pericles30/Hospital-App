package com.example.hospital2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hospital2.databinding.ActivityBookServiceBinding

class BookServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the selected service from the intent
        val selectedService = intent.getStringExtra("service")

        // Display the selected service
        binding.selectedServiceTextView.text = "Booking for: $selectedService"
    }
}
