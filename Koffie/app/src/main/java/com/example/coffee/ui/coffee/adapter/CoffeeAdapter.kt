package com.example.coffee.ui.coffee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.ui.coffee.CoffeeActivity
import kotlinx.android.synthetic.main.model_coffee_card.view.*


class CoffeeAdapter(
    private val coffeeList: ArrayList<ArrayList<Coffee>>,
    private val callbackEditCoffee: (coffee: ArrayList<Coffee>) -> Unit,
    private val callbackLoadImages: (coffee: Coffee, imageView: ImageView) -> Unit
) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cardView: LinearLayout = view.findViewById(R.id.linearLayoutTable)

        fun bind(coffeeByDay: ArrayList<Coffee>) {
            // On long click coffee go to method to edit coffee of today
            itemView.setOnLongClickListener {
                callbackEditCoffee(coffeeByDay)
                true
            }

            // Check if date is today or yesterday
            when (coffeeByDay[0].date) {
                CoffeeActivity.today() -> itemView.tvDay.text = context.getString(R.string.adapter_coffee_today)
                CoffeeActivity.yesterday() -> itemView.tvDay.text = context.getString(R.string.adapter_coffee_yesterday)
                else -> itemView.tvDay.text = coffeeByDay[0].date
            }

            // for each coffee in list make an new row and add it to the view
            coffeeByDay.forEach {
                if (it.amount != 0) {
                    // New row
                    val coffeeRow = LayoutInflater.from(context).inflate(R.layout.model_coffee_row, cardView, false)
                    callbackLoadImages(it, coffeeRow.findViewById(R.id.imgCoffee)) // Load image
                    coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = it.type // Set type
                    coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text = it.amount.toString() // Set amount
                    cardView.addView(coffeeRow) // Add it to the card
                }
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
