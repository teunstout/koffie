package com.example.coffee.ui.coffee.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.model.database_model.CoffeeChoice
import com.example.coffee.ui.coffee.CoffeeActivity
import com.example.coffee.ui.coffee.adapter.AddCoffeeAdapter
import com.example.coffee.ui.coffee.viewmodel.AddCoffeeViewModel
import kotlinx.android.synthetic.main.activity_add_coffee.*
import kotlin.collections.ArrayList

class AddCoffeeActivity : AppCompatActivity() {
    private val addCoffeeViewModel: AddCoffeeViewModel by viewModels()
    private var coffeeChoiceList = ArrayList<CoffeeChoice>()
    private val addCoffeeAdapter = AddCoffeeAdapter(coffeeChoiceList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coffee)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Back button

        initView()
    }

    private fun initView() {
        // Set adapter
        viewPagerCoffee.adapter = addCoffeeAdapter

        // Add observer on choices.
        addCoffeeViewModel.choices.observe(this, androidx.lifecycle.Observer { listCoffeeChoces ->
            coffeeChoiceList.clear()
            if (!listCoffeeChoces.isNullOrEmpty()) {
                coffeeChoiceList.addAll(listCoffeeChoces)
                addCoffeeAdapter.notifyDataSetChanged()
            }
        })

        btnConfirm.setOnClickListener {
            checkIfValidAndSave()
        }
    }

    /**
     * Check if all the
     */
    private fun checkIfValidAndSave() {
        // If no amount is filled in return and display message.
        if (itAmount.text.isNullOrEmpty()) return toastMessage(getString(R.string.activity_add_coffee_no_amount))

        // If coffee choices are empty you can't save anything.
        if (coffeeChoiceList.isEmpty())
            return toastMessage(getString(R.string.activity_add_coffee_no_img))


        // New coffee
        val coffeeToSave = Coffee(
            coffeeChoiceList[viewPagerCoffee.currentItem].coffeeType,
            CoffeeActivity.today(),
            itAmount.text.toString().toInt(),
            coffeeChoiceList[viewPagerCoffee.currentItem].coffeeImgUrl
        )
        // Save coffee or update it
        addCoffeeViewModel.saveCoffee(coffeeToSave)

        // Start new coffee intent and finish this one.
        // We do this because we need to rebuild the views.
        // Otherwise old data will be displayed with the new data.
        val newCoffeeIntent = Intent(this, CoffeeActivity::class.java)
        startActivity(newCoffeeIntent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}