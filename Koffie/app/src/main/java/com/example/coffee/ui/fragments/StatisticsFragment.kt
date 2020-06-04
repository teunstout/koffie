package com.example.coffee.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.ui.CoffeeViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.model_coffee_card.*

class StatisticsFragment : Fragment() {
    private val coffeeViewModel: CoffeeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tvTitleStatistics.text = getString(R.string.fragment_statistics_title)

        coffeeViewModel.totalPerCoffee.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                coffeeCard.visibility = View.VISIBLE
                buildStatisticsView(it)
            } else coffeeCard.visibility = View.INVISIBLE
        })

//        coffeeViewModel.totalAllCoffeeInt.observe(viewLifecycleOwner, Observer {
//            buildSmileyMessageView(it)
//        })
    }

//    private fun buildSmileyMessageView(amount: Int) {
//        when(amount){
//            0 -> setImageWithText(R.drawable.disappointment, getString(R.string.smiley_disappointment))
//            1, 2 -> setImageWithText(R.drawable.thinking, getString(R.string.smiley_thinking))
//            in 3..6 -> setImageWithText(R.drawable.smile, getString(R.string.smiley_smile))
//            in 7..10 -> setImageWithText(R.drawable.nervous, getString(R.string.smiley_nervous))
//            else -> setImageWithText(R.drawable.shocked, getString(R.string.smiley_shocked))
//        }
//    }

//    private fun setImageWithText(imageInt: Int, message: String){
//
//    }

    private fun buildStatisticsView(coffeeTotalList: List<Coffee>) {
        val rootLayout: LinearLayout? = view?.findViewById(R.id.linearLayoutTable)
        coffeeTotalList.forEach {
            val coffeeRow = LayoutInflater.from(this.context).inflate(R.layout.model_coffee_row, rootLayout, false)
            coffeeRow.findViewById<ImageView>(R.id.imgCoffee).setImageResource(it.imgId)
            coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = it.type
            coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text = it.amount.toString()
            rootLayout?.addView(coffeeRow)
        }
    }
}
