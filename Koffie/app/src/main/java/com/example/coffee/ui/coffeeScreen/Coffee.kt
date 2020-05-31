package com.example.coffee.ui.coffeeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coffee.R

class Coffee : Fragment() {

    private lateinit var homeViewModel: CoffeeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(CoffeeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_coffee, container, false)
    }
}
