package com.example.coffee.ui.fragments.coffeeFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.ui.CoffeeViewModel
import com.example.coffee.ui.fragments.coffeeFragment.addCoffeeActivity.AddCoffeeActivity
import kotlinx.android.synthetic.main.fragment_coffee.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeFragment : Fragment() {
    private val coffeeViewModel: CoffeeViewModel by activityViewModels()
    private var coffeeList: ArrayList<ArrayList<Coffee>> = ArrayList()
    private var coffeeAdapter = CoffeeAdapter(coffeeList)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        rvCoffee.adapter = coffeeAdapter
        rvCoffee.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        coffeeViewModel.coffee.observe(viewLifecycleOwner, Observer {
            addCoffeeSortedToList(it as ArrayList<Coffee>)
        })
    }

    private fun addCoffeeSortedToList(allCoffee: ArrayList<Coffee>) {
        if (allCoffee.isNullOrEmpty() || allCoffee.size == 0) return // check if list is empty and return if so

        coffeeList.clear()

        CoroutineScope(Dispatchers.Main).launch {
            var coffeeListDateDay = "" // Date of last inserted coffee
            var coffeeListIndex = -1

            allCoffee.forEach {
                if (coffeeListDateDay == it.date) {
                    coffeeList[coffeeListIndex].add(it)
                } else {
                    coffeeListDateDay = it.date
                    coffeeList.add(ArrayList<Coffee>())
                    coffeeList[++coffeeListIndex].add(it)
                }
            }
        }

        coffeeAdapter.notifyDataSetChanged()
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
