package com.example.coffee.ui.coffeeScreens

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffee.R
import com.example.coffee.model.MuseumObject
import kotlinx.android.synthetic.main.model_museum_object.view.*

class CoffeeAdapter(private val artifacts: List<MuseumObject>) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(artifact: MuseumObject) {
            itemView.tvTitle.text = artifact.title
            itemView.tvCreator.text = artifact.creator
            itemView.tvDescription.text = artifact.description
            Log.i("URL", artifact.pictureUrl.url)
            Glide.with(context).load(artifact.pictureUrl.url).into(itemView.imgObject)
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