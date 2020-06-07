package com.example.coffee.ui.coffee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.coffee.R
import com.example.coffee.ui.coffee.activity.AddCoffeeActivity
import kotlinx.android.synthetic.main.activity_coffee.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val RESTART_ACTIVITY = 100

class CoffeeActivity : AppCompatActivity() {
    companion object {
        // Date string as companion object so every time we use it in format it is the same.
        private const val DATE_STRING = "dd-MM-yyyy"

        // Get the date of today.
        fun today(): String {
            val format = DateTimeFormatter.ofPattern(DATE_STRING)
            return LocalDate.now().format(format)
        }

        // Get the date of yesterday.
        fun yesterday(): String {
            val format = DateTimeFormatter.ofPattern(DATE_STRING)
            return LocalDate.now().minusDays(1).format(format)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)

        initNavigation()
    }

    /**
     * Inflate the menu with custom menu.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.coffee_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun initNavigation() {
        // The NavController
        val navController = findNavController(R.id.navHostFragment)

        // Connect the navHostFragment with the BottomNavigationView.
        NavigationUI.setupWithNavController(navView, navController)

        // Switch out the menu for each fragment.
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.coffeeFragment -> supportActionBar?.show()
                R.id.statisticsFragment -> supportActionBar?.hide()
                R.id.museumFragment -> supportActionBar?.hide()
                R.id.addCoffeeChoiceFragment -> supportActionBar?.hide()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addCoffee -> {
                val addCoffee = Intent(this, AddCoffeeActivity::class.java)
                startActivityForResult(addCoffee, RESTART_ACTIVITY)
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * Restart the coffee activity.
     * Each time data changes it pushes data instead of update.
     * Known problem, so we have to update activity.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RESTART_ACTIVITY) {
                val newCoffeeIntent = Intent(this, CoffeeActivity::class.java)
                startActivity(newCoffeeIntent)
                finish()
            }
        }
    }

}