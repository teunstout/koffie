package com.example.coffee.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.ui.CoffeeViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*

class StatisticsFragment : Fragment() {
    private val coffeeViewModel: CoffeeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        tvTitleStatistics.text = getString(R.string.fragment_statistics_title)
        coffeeViewModel.totalCoffee.observe(viewLifecycleOwner, Observer {
            buildView(view, it)
        })
    }

    override fun onResume() {
        super.onResume()
        view?.findViewById<LinearLayout>(R.id.linearLayoutTable)?.invalidate()
    }

    private fun buildView(view: View, coffeeTotalList: List<Coffee>) {
        val rootLayout: LinearLayout = view.findViewById(R.id.linearLayoutTable)
        coffeeTotalList.forEach {
            val coffeeRow = LayoutInflater.from(this.context).inflate(R.layout.model_coffee_row, rootLayout, false)
            coffeeRow.findViewById<ImageView>(R.id.imgCoffee).setImageResource(it.imgId)
            coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = it.type
            coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text = it.amount.toString()
            rootLayout.addView(coffeeRow)
        }
    }
}
