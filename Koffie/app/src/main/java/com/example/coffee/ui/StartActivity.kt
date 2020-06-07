package com.example.coffee.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.model.database_model.CoffeeChoice
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.schedule
import kotlin.math.log


class StartActivity : AppCompatActivity() {
    private val startViewModel: StartViewModel by viewModels()
    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var dbCoffeeChoices = database?.getReference("Choices")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initView()

        Timer().schedule(1000) {
            val coffeeActivity = Intent(this@StartActivity, CoffeeActivity::class.java) // Activity
            startActivity(coffeeActivity)
        }
    }

    private fun initView() {
        dbCoffeeChoices?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val values = dataSnapshot.children

                CoroutineScope(Dispatchers.Main).launch {
                    values.forEach { value ->
                        val newCoffeeChoice =
                            value.key?.let { it -> CoffeeChoice(it, value.value as String) }
                        withContext(Dispatchers.IO) {
                            if (newCoffeeChoice != null) {
                                startViewModel.insertCoffeeChoice(newCoffeeChoice)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i("VALUEOFZ", "Failed to read value.", error.toException())
            }
        })
    }

}
