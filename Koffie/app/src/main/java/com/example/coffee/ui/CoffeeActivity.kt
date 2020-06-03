package com.example.coffee.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.coffee.R
import kotlinx.android.synthetic.main.activity_coffee.*
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter


class CoffeeActivity : AppCompatActivity() {
    companion object {
        const val DATE_STRING = "dd-MM-yyyy"
        fun today(): String {
            val format = DateTimeFormatter.ofPattern(CoffeeActivity.DATE_STRING)
            return LocalDate.now().format(format)
        }
        fun yesterday(): String {
            val format = DateTimeFormatter.ofPattern(CoffeeActivity.DATE_STRING)
            return LocalDate.now().minusDays(1).format(format)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)

        initNavigation()
    }

    private fun initNavigation() {
        // The NavController
        val navController = findNavController(R.id.navHostFragment)

        // Connect the navHostFragment with the BottomNavigationView.
        NavigationUI.setupWithNavController(navView, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.coffeeFragment -> supportActionBar?.show()
                R.id.statisticsFragment -> supportActionBar?.hide()
                R.id.museumFragment -> supportActionBar?.hide()
            }
        }
    }


}