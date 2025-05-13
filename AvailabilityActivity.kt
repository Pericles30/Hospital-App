package com.example.hospital2


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hospital2.ui.gallery.GalleryFragment

class AvailabilityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_availability)

        // Set window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the back button and set an OnClickListener to go back to GalleryFragment
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Create an intent to go back to GalleryFragment
            val intent = Intent(this@AvailabilityActivity, GalleryFragment::class.java)
            intent.putExtra("FRAGMENT", "GalleryFragment") // Pass a flag to open GalleryFragment
            startActivity(intent)
            finish() // Close this activity to prevent it from stacking
        }
    }
}
