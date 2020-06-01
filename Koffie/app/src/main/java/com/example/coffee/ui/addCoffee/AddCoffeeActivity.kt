package com.example.coffee.ui.addCoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.android.synthetic.main.activity_add_coffee.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class AddCoffeeActivity : AppCompatActivity() {
    private val addCoffeeViewModel: AddCoffeeViewModel by viewModels()
    private var coffeeChoiceList = ArrayList<CoffeeChoice>()
    private val coffeeChoiceAdapter = CoffeeChoiceAdapter(coffeeChoiceList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coffee)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViewModel()
        initView()
    }

    private fun initViewModel() {
        viewPagerCoffee.adapter = coffeeChoiceAdapter // Set adapter

        // Observe LiveData
        addCoffeeViewModel.choices.observe(this, androidx.lifecycle.Observer {
            coffeeChoiceList.clear()
            if (!it.isNullOrEmpty()) {
                coffeeChoiceList.addAll(it)
                coffeeChoiceAdapter.notifyDataSetChanged()
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
        addCoffeeViewModel.saveCoffee(
            coffeeChoiceList[viewPagerCoffee.currentItem],
            itAmount.text.toString()
        )
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}