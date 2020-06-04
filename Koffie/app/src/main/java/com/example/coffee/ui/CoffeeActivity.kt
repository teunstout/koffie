package com.example.coffee.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.coffee.R
import com.example.coffee.ui.fragments.coffeeFragment.addCoffeeActivity.AddCoffeeActivity
import kotlinx.android.synthetic.main.activity_coffee.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CoffeeActivity : AppCompatActivity() {
    companion object {
        const val DATE_STRING = "dd-MM-yyyy"
        fun today(): String {
            val format = DateTimeFormatter.ofPattern(DATE_STRING)
            return LocalDate.now().format(format)
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.coffee_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestart() {
        super.onRestart()
        val newCoffeeIntent = Intent(this, CoffeeActivity::class.java)
        startActivity(newCoffeeIntent)
        Log.i("FinishIntent", "OnRestart")
        finish()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.addCoffee -> {
                val addCoffee = Intent(this, AddCoffeeActivity::class.java)
                startActivity(addCoffee)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}