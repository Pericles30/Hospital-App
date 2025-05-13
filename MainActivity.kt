package com.example.hospital2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hospital2.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Set the NoActionBar theme before calling super.onCreate()
        setTheme(R.style.Theme_Hospital2_NoActionBar)

        super.onCreate(savedInstanceState)

        // Get the username from the login activity
        val intent: Intent = intent
        username = intent.getStringExtra("USERNAME")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the custom toolbar
        setSupportActionBar(binding.appBarMain.toolbar)

        // Set up FAB click listener
        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Email: Info@carecurehospital.com", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(binding.appBarMain.fab).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of IDs because each menu should be considered as top-level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_profile
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Update the username in the navigation header
        val headerView: View = navView.getHeaderView(0)
        val navUsername = headerView.findViewById<TextView>(R.id.nav_username)

        // Check if navUsername is not null before setting the text
        navUsername?.let {
            it.text = username
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Logout -> {
                // Handle logout
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun logout() {
        // Clear any user session data (e.g., SharedPreferences, database)
        // ...

        // Navigate back to the login page
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()
        finish() // Optional: Finish the current activity
    }
}