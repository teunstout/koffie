package com.example.coffee.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.R
import com.example.coffee.model.database_model.CoffeeChoice
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

//    private val allCoffee = arrayListOf(
//        CoffeeChoice("COFFEE", R.drawable.coffee),
//        CoffeeChoice("CAPPUCCINO", R.drawable.cappuccino),
//        CoffeeChoice("ESPRESSO", R.drawable.espresso)
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.IO) {
//                allCoffee.forEach {
//                    mainActivityViewModel.insertCoffeeChoice(it)
//                }
//            }
//        }

        Timer().schedule(1000) {
            val coffeeActivity = Intent(this@MainActivity, CoffeeActivity::class.java) // Activity
            startActivity(coffeeActivity)
        }
    }

}
