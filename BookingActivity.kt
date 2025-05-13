package com.example.hospital2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hospital2.ui.gallery.GalleryFragment

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Edge-to-edge UI handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.et_name)
        val etPhone = findViewById<EditText>(R.id.et_phone)
        val radioGroupBedType = findViewById<RadioGroup>(R.id.radioGroupBedType)
        val checkboxWifi = findViewById<CheckBox>(R.id.checkboxWifi)
        val checkboxMealService = findViewById<CheckBox>(R.id.checkboxMealService)
        val checkboxNurse24 = findViewById<CheckBox>(R.id.checkboxNurse24)
        val spinnerDays = findViewById<Spinner>(R.id.spinnerDays)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val buttonBack = findViewById<Button>(R.id.buttonGoBack) // New button to go back
        val textViewBookingDetails = findViewById<TextView>(R.id.textViewBookingDetails)

        buttonSubmit.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()

            // Get selected bed type
            val selectedBedTypeId = radioGroupBedType.checkedRadioButtonId
            val selectedBedType = findViewById<RadioButton>(selectedBedTypeId).text.toString()

            // Get selected additional facilities
            val facilities = mutableListOf<String>()
            if (checkboxWifi.isChecked) facilities.add("Wi-Fi")
            if (checkboxMealService.isChecked) facilities.add("Meal Service")
            if (checkboxNurse24.isChecked) facilities.add("24/7 Nurse Assistance")

            // Get selected number of days
            val selectedDays = spinnerDays.selectedItem.toString()

            // Format booking details
            val bookingDetails = """
                Booking Details:
                Name: $name
                Phone: $phone
                Bed Type: $selectedBedType
                Facilities: ${if (facilities.isEmpty()) "None" else facilities.joinToString(", ")}
                Number of Days: $selectedDays
            """.trimIndent()

            // Display booking details
            textViewBookingDetails.text = bookingDetails
        }

        // Back button to navigate to GalleryFragment
        buttonBack.setOnClickListener {
            // Intent to go back to GalleryFragment
            val intent = Intent(this, GalleryFragment::class.java)
            startActivity(intent)
        }
    }
}
