package com.example.coffee.ui.fragments.coffee_fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.ui.CoffeeActivity
import com.example.coffee.ui.CoffeeActivityViewModel
import com.example.coffee.ui.fragments.coffee_fragment.update_coffee_activity.UpdateCoffeeActivity
import kotlinx.android.synthetic.main.fragment_coffee.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val RESTART_ACTIVITY = 100

class CoffeeFragment : Fragment() {
    private val coffeeActivityViewModel: CoffeeActivityViewModel by activityViewModels()
    private var coffeeList: ArrayList<ArrayList<Coffee>> = ArrayList()
    private var coffeeAdapter =
        CoffeeAdapter(coffeeList) { coffeeListEdit -> editCoffee(coffeeListEdit) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        rvCoffee.adapter = coffeeAdapter
        rvCoffee.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        // Observers
        coffeeActivityViewModel.coffee.observe(viewLifecycleOwner, Observer {
            addCoffeeSortedToList(it as ArrayList<Coffee>)
        })

        coffeeActivityViewModel.totalAllCoffeeInt.observe(viewLifecycleOwner, Observer {
            if (it == null) buildSmileyMessageView(0)
            else buildSmileyMessageView(it)

        })
    }

    private fun buildSmileyMessageView(amount: Int) {
        when (amount) {
            0 -> setImageWithText(
                R.drawable.disappointment,
                getString(R.string.smiley_disappointment)
            )
            1, 2 -> setImageWithText(R.drawable.thinking, getString(R.string.smiley_thinking))
            in 3..6 -> setImageWithText(R.drawable.smile, getString(R.string.smiley_smile))
            in 7..10 -> setImageWithText(R.drawable.nervous, getString(R.string.smiley_nervous))
            else -> setImageWithText(R.drawable.shocked, getString(R.string.smiley_shocked))
        }
    }

    private fun setImageWithText(imageInt: Int, message: String) {
        imgSmiley.setImageResource(imageInt)
        tvMessage.text = message
    }

    private fun editCoffee(coffeeListEdit: ArrayList<Coffee>) {
        val updateCoffeeIntent = Intent(this.context, UpdateCoffeeActivity::class.java)
        updateCoffeeIntent.putParcelableArrayListExtra(
            UpdateCoffeeActivity.COFFEE_LIST,
            coffeeListEdit
        )
        startActivityForResult(updateCoffeeIntent, RESTART_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESTART_ACTIVITY) {
            activity?.finish()
            val newCoffeeIntent = Intent(this.context, CoffeeActivity::class.java)
            startActivity(newCoffeeIntent)
        }
    }

    private fun addCoffeeSortedToList(allCoffee: ArrayList<Coffee>) {
        view?.invalidate()
        if (allCoffee.isNullOrEmpty() || allCoffee.size == 0) return // check if list is empty and return if so
        coffeeList.clear()

        CoroutineScope(Dispatchers.Default).launch {
            var coffeeListDateDay = "" // Date of last inserted coffee
            var coffeeListIndex = -1

            allCoffee.reversed().forEach {
                if (coffeeListDateDay == it.date) {
                    coffeeList[coffeeListIndex].add(it)
                } else {
                    coffeeListDateDay = it.date
                    coffeeList.add(ArrayList())
                    coffeeList[++coffeeListIndex].add(it)
                }
            }
        }

        coffeeAdapter.notifyDataSetChanged()
    }
}
