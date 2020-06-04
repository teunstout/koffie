package com.example.coffee.ui.fragments.coffeeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.Coffee
import com.example.coffee.ui.CoffeeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.model_coffee_card.view.*


class CoffeeAdapter(
    private val coffeeList: ArrayList<ArrayList<Coffee>>,
    private val callbackEditCoffee: (coffee: ArrayList<Coffee>) -> Unit
) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val rootLayout: LinearLayout = view.findViewById(R.id.linearLayoutTable)
        fun bind(coffeeByDay: ArrayList<Coffee>) {
            itemView.setOnLongClickListener {
                callbackEditCoffee(coffeeByDay)
                true
            }
            when (coffeeByDay[0].date) {
                CoffeeActivity.today() -> itemView.tvDay.text = "Today"
                CoffeeActivity.yesterday() -> itemView.tvDay.text = "Yesterday"
                else -> itemView.tvDay.text = coffeeByDay[0].date
            }

            coffeeByDay.forEach {
                val coffeeRow = LayoutInflater.from(context)
                    .inflate(R.layout.model_coffee_row, rootLayout, false)
                coffeeRow.findViewById<ImageView>(R.id.imgCoffee).setImageResource(it.imgId)
                coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = it.type
                coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text = it.amount.toString()
                rootLayout.addView(coffeeRow)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.model_coffee_card, parent, false)
        )
    }

    override fun getItemCount(): Int =
        coffeeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(coffeeList[position])
}
