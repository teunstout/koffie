package com.example.coffee.ui.fragments.coffeeFragment.editCoffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee
import kotlinx.android.synthetic.main.activity_update_coffee.*
import kotlinx.android.synthetic.main.model_coffee_card.*
import kotlinx.android.synthetic.main.model_coffee_card.view.*

class UpdateCoffeeActivity : AppCompatActivity() {
    companion object {
        const val COFFEE_LIST = "COFFEE_LIST"
    }

    private var listCoffee = ArrayList<Coffee>()
    private val updateCoffeeActivity: UpdateCoffeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_coffee)

        initView()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_coffee_menu, menu)
        supportActionBar?.show()
        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        listCoffee = intent.getParcelableArrayListExtra(COFFEE_LIST)
        if (listCoffee.isNullOrEmpty()) finish()
        addItemToCard()

        btnUpdate.setOnClickListener {
            listCoffee.forEach {
                updateCoffeeActivity.insertCoffee(it)
            }
            finish()
        }
    }

    private fun addItemToCard() {
        val card = findViewById<LinearLayout>(R.id.linearLayoutTable)
        listCoffee.forEachIndexed { index, coffee ->

            val coffeeRow = LayoutInflater.from(this)
                .inflate(R.layout.model_coffee_row_update, card, false)

            coffeeRow.findViewById<ImageView>(R.id.imgCoffee).setImageResource(coffee.imgId)
            coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = coffee.type
            coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text = coffee.amount.toString()

            coffeeRow.findViewById<Button>(R.id.btnAdd).setOnClickListener {
                listCoffee[index].amount++
                coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text =
                    listCoffee[index].amount.toString()
            }

            coffeeRow.findViewById<Button>(R.id.btnRemove).setOnClickListener {
                listCoffee[index].amount--
                coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text =
                    listCoffee[index].amount.toString()
            }

            card.addView(coffeeRow)
        }
    }

}