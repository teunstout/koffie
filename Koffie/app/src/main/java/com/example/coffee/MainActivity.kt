package com.example.coffee

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private val coffeeViewModel: CoffeeViewModel by viewModels()
    private val allCoffee = arrayListOf<CoffeeChoice>(
        CoffeeChoice(R.drawable.coffee, "COFFEE"),
        CoffeeChoice(R.drawable.cappuccino, "CAPPUCCINO"),
        CoffeeChoice(R.drawable.espresso, "ESPRESSO")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                allCoffee.forEach { coffeeViewModel.insertCoffee(it) }
            }
        }


        val coffeeActivity = Intent(this, CoffeeActivity::class.java) // Activity
        Timer().schedule(1000) {
            startActivity(coffeeActivity) // Start activity after 1 sec
        }
    }

}
