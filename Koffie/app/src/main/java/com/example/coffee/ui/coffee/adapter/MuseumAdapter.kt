package com.example.coffee.ui.coffee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffee.R
import com.example.coffee.model.museum_model.MuseumArtifact
import kotlinx.android.synthetic.main.model_museum_object.view.*

class MuseumAdapter(
    private val artifacts: List<MuseumArtifact>,
    private val callbackNextPage: () -> Unit
) :
    RecyclerView.Adapter<MuseumAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val sizeList = artifacts.size - 1 // -1 Because we want to start at index 0

        fun bind(artifact: MuseumArtifact) {
            itemView.tvTitle.text = artifact.title // Title
            itemView.tvCreator.text = artifact.longTitle // Creator

            // Place where artifact was made
            if (artifact.productionPlace.isNotEmpty()) {
                // Production place gives back array. Seen from json request we need the last one of the list.
                // https://www.rijksmuseum.nl/api/nl/collection?key=h8kfsPso&format=json&ps=10&p=1
                val lastDescription: Int = artifact.productionPlace.size - 1
                // Populate text field
                itemView.tvCreated.text = context.getString(
                    R.string.fragment_museum_txt_string,
                    artifact.productionPlace[lastDescription]
                )
            }

            // Load images
            Glide.with(context).load(artifact.pictureUrl.url)
                .into(itemView.imgObject)

            // End of list get new data
            if (adapterPosition == sizeList) {
                callbackNextPage()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.model_museum_object, parent, false)
        )
    }

    override fun getItemCount(): Int = artifacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(artifacts[position])
}