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

        // The data is retrieved to quick, so just start activity after 1 second
        Timer().schedule(1000) {
            val coffeeActivity = Intent(this@StartActivity, CoffeeActivity::class.java) // Activity
            startActivity(coffeeActivity)
        }
    }

    private fun initView() {
        // Observe if the data from the local database is here
        startViewModel.coffeeChoices.observe(this@StartActivity, androidx.lifecycle.Observer {
            // Data arrived. Add listener on online database and get data
            dbCoffeeChoices?.addValueEventListener(object : ValueEventListener {
                // Check for datachanges. Happens on startup and when something changes
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // All the data from the database
                    val coffeeChoiceRows = dataSnapshot.children

                    CoroutineScope(Dispatchers.Main).launch {
                        // For each row make coffee object and save it
                        coffeeChoiceRows.forEach { row ->
                            val newCoffeeChoice =
                                row.key?.let { it -> CoffeeChoice(it, row.value as String) }
                            if (newCoffeeChoice != null) {
                                saveCoffeeChoice(newCoffeeChoice)
                            }
                        }
                    }
                    Log.d(
                        "SaveFirebase",
                        "Edit of local database data with firenbase data is completed."
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("ErrorFirebase", "Failed to read value.", error.toException())
                }
            })
        })
    }

    /**
     * For each new coffee check if it already exists.
     * if it does but url is different update.
     * if it does not exists insert coffee
     */
    private fun saveCoffeeChoice(newCoffeeChoice: CoffeeChoice) {
        CoroutineScope(Dispatchers.Main).launch {
            // Filter data
            val localDatabaseCoffeeChoice = startViewModel.coffeeChoices.value?.filter {
                it.coffeeType == newCoffeeChoice.coffeeType
            }

            // Only is one coffeeChoice because the type is the primary key.
            if (localDatabaseCoffeeChoice.isNullOrEmpty()) withContext(Dispatchers.IO) {
                startViewModel.insertCoffeeChoice(
                    newCoffeeChoice
                )
            }
            else {
                if (localDatabaseCoffeeChoice[0].coffeeImgUrl != newCoffeeChoice.coffeeImgUrl)
                    withContext(Dispatchers.IO) { startViewModel.updateCoffeeChoice(newCoffeeChoice) }
            }
        }
    }
}
