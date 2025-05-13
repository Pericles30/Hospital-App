package com.example.hospital2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.example.hospital2.ui.slideshow.SlideshowFragment

class ServiceBooking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_booking)

        val serviceRadioGroup = findViewById<RadioGroup>(R.id.serviceRadioGroup)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val backButton = findViewById<Button>(R.id.backButton)

        submitButton.setOnClickListener {
            val selectedId = serviceRadioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedService = when (selectedId) {
                    R.id.emergencyRadioButton -> "Emergency Service"
                    R.id.icuRadioButton -> "ICU Service"
                    R.id.generalSurgeryRadioButton -> "General Surgery"
                    R.id.maternityRadioButton -> "Maternity Service"
                    else -> "Unknown Service"
                }
                Toast.makeText(this, "You have booked: $selectedService", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a service", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}