package com.example.coffee.ui.coffeeScreens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.rijksMuseumObjects.MuseumObject

class CoffeeAdapter(private val artifacts: List<MuseumObject>) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(artifact: MuseumObject) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.model_museum_object, parent, false)
        )
    }
    override fun getItemCount(): Int =
        artifacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(artifacts[position])
}