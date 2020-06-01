package com.example.coffee.ui.coffeeScreens

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coffee.R
import com.example.coffee.CoffeeViewModel
import com.example.coffee.ui.addCoffee.AddCoffeeActivity

class CoffeeFragment : Fragment() {

    private lateinit var coffeeViewModel: CoffeeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        coffeeViewModel = ViewModelProvider(this).get(CoffeeViewModel::class.java)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_coffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.coffee_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initView() {
        // Data
        observerCoffeeAmount()

        // Recyclerview
    }

    private fun observerCoffeeAmount() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.addCoffee -> {
                val addCoffee = Intent(this.context, AddCoffeeActivity::class.java)
                startActivity(addCoffee)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
