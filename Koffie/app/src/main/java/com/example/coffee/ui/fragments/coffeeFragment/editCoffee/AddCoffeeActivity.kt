package com.example.coffee.ui.fragments.coffeeFragment.editCoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.android.synthetic.main.activity_add_coffee.*
import kotlin.collections.ArrayList

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
        viewPagerCoffee.adapter = coffeeChoiceAdapter
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
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}