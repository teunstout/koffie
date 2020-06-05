package com.example.coffee.ui.fragments.coffee_fragment.update_coffee_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.ui.CoffeeActivity
import kotlinx.android.synthetic.main.activity_update_coffee.*


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteCoffee -> {
                AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setTitle("Delete all coffee for ${CoffeeActivity.today()}?")
                    .setMessage("After you deleted your coffee, there is no way to get it back")
                    .setPositiveButton("Yes") { dialog, which ->
                        listCoffee.forEach {
                            updateCoffeeActivity.deleteCoffee(it)
                        }
                        finish()

                    }
                    .setNegativeButton("No", null)
                    .show()
                return false
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }
}