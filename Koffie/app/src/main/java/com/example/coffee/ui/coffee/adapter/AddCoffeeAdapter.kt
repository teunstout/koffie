package com.example.coffee.ui.coffee.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.coffee.R
import com.example.coffee.model.database_model.CoffeeChoice
import kotlinx.android.synthetic.main.model_coffee_choice.view.*

class AddCoffeeAdapter(private val choices: ArrayList<CoffeeChoice>) :
    RecyclerView.Adapter<AddCoffeeAdapter.ViewHolder>() {
    lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(choice: CoffeeChoice) {
            itemView.tvModelCoffeeTitle.text = choice.coffeeType

            Glide.with(context).load(choice.coffeeImgUrl).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(context, "Coulden't find image of ${choice.coffeeType}", Toast.LENGTH_LONG).show()
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(itemView.imgCoffeeChoice)
//            itemView.imgCoffeeChoice.setImageResource(choice.coffeeImgUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.model_coffee_choice, parent, false)
        )
    }

    override fun getItemCount(): Int = choices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(choices[position])
}