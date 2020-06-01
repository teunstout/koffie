package com.example.coffee.ui.addCoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.android.synthetic.main.activity_add_coffee.*

class AddCoffeeActivity : AppCompatActivity() {
    private val addCoffeeViewModel: AddCoffeeViewModel by viewModels()
    private var coffeeChoiceList = ArrayList<CoffeeChoice>()
    private val coffeeChoiceAdapter = CoffeeChoiceAdapter(coffeeChoiceList)
    private val allCoffee = arrayListOf(
        CoffeeChoice(R.drawable.coffee, "COFFEE"),
        CoffeeChoice(R.drawable.cappuccino, "CAPPUCCINO"),
        CoffeeChoice(R.drawable.espresso, "ESPRESSO")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coffee)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        initViewPager()
    }

    private fun initViewPager() {
        coffeeChoiceList.clear()
        coffeeChoiceList.addAll(allCoffee)
        viewPagerCoffee.adapter = coffeeChoiceAdapter
        coffeeChoiceAdapter.notifyDataSetChanged()

    }

    private fun initView() {
        addCoffeeViewModel.getAllCoffeeChoices()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}