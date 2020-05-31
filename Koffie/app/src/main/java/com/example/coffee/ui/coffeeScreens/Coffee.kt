package com.example.coffee.ui.coffeeScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coffee.R

class Coffee : Fragment() {

    private lateinit var coffeeViewModel: CoffeeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        coffeeViewModel = ViewModelProvider(this).get(CoffeeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_coffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        // Data
        observerCoffeeAmount()

        // Recyclerview
    }

    private fun observerCoffeeAmount() {

    }
}
