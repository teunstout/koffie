package com.example.coffee.ui.fragments.coffeeFragment.editCoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee

class UpdateCoffeeActivity : AppCompatActivity() {
    companion object{
        const val COFFEE_LIST = "COFFEE_LIST"
    }

    private val updateCoffeeActivity: UpdateCoffeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_coffee)

        initView()
    }

    private fun initView() {
        val listCoffee: ArrayList<Coffee> = intent.getParcelableArrayListExtra(COFFEE_LIST)
        if (listCoffee.isNullOrEmpty()) finish()
    }

}