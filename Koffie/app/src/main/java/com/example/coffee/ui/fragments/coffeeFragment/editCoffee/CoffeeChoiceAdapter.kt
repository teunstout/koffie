package com.example.coffee.ui.fragments.coffeeFragment.editCoffee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.databaseObjects.CoffeeChoice
import kotlinx.android.synthetic.main.model_coffee_choice.view.*

class CoffeeChoiceAdapter(private val choices: ArrayList<CoffeeChoice>) :
    RecyclerView.Adapter<CoffeeChoiceAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(choice: CoffeeChoice) {
            itemView.tvModelCoffeeTitle.text = choice.coffeeName
            itemView.imgCoffeeChoice.setImageResource(choice.coffeeImgInt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.model_coffee_choice, parent, false)
        )
    }

    override fun getItemCount(): Int = choices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(choices[position])
}