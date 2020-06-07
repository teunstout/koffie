package com.example.coffee.ui.coffee.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private val addCoffeeAdapter =
        AddCoffeeAdapter(coffeeChoiceList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coffee)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViewModel()
        initView()
    }

    private fun initViewModel() {
        viewPagerCoffee.adapter = addCoffeeAdapter
        addCoffeeViewModel.choices.observe(this, androidx.lifecycle.Observer {
            coffeeChoiceList.clear()
            if (!it.isNullOrEmpty()) {
                coffeeChoiceList.addAll(it)
                addCoffeeAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initView() {
        btnConfirm.setOnClickListener {
            checkIfValidAndSave()
        }
    }

    private fun checkIfValidAndSave() {
        if (itAmount.text.isNullOrEmpty()) return

        val coffeeToSave = Coffee(
            coffeeChoiceList[viewPagerCoffee.currentItem].coffeeType,
            CoffeeActivity.today(),
            coffeeChoiceList[viewPagerCoffee.currentItem].toString().toInt(),
            coffeeChoiceList[viewPagerCoffee.currentItem].coffeeImgUrl
        )
        addCoffeeViewModel.saveCoffee(coffeeToSave)

        val newCoffeeIntent = Intent(this, CoffeeActivity::class.java)
        startActivity(newCoffeeIntent)


        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}