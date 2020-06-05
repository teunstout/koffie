package com.example.coffee.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.R
import com.example.coffee.model.database_model.CoffeeChoice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule

class StartActivity : AppCompatActivity() {
    private val startViewModel: StartViewModel by viewModels()

    private val allCoffee = arrayListOf(
        CoffeeChoice("COFFEE", R.drawable.coffee),
        CoffeeChoice("CAPPUCCINO", R.drawable.cappuccino),
        CoffeeChoice("ESPRESSO", R.drawable.espresso)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                allCoffee.forEach {
                    startViewModel.insertCoffeeChoice(it)
                }
            }
        }

        Timer().schedule(1000) {
            val coffeeActivity = Intent(this@StartActivity, CoffeeActivity::class.java) // Activity
            startActivity(coffeeActivity)
        }
    }

}
