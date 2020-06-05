package com.example.coffee.ui.fragments.coffee_fragment.add_coffee_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.database_model.CoffeeChoice
import kotlinx.android.synthetic.main.model_coffee_choice.view.*

class AddCoffeeAdapter(private val choices: ArrayList<CoffeeChoice>) :
    RecyclerView.Adapter<AddCoffeeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(choice: CoffeeChoice) {
            itemView.tvModelCoffeeTitle.text = choice.coffeeType
            itemView.imgCoffeeChoice.setImageResource(choice.coffeeImgId)
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