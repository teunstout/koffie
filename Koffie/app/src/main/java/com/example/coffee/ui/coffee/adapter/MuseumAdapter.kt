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

class MuseumAdapter(private val artifacts: List<MuseumArtifact>) :
    RecyclerView.Adapter<MuseumAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(artifact: MuseumArtifact) {
            itemView.tvTitle.text = artifact.title // Title
            itemView.tvCreator.text = artifact.creator // Creator
            itemView.tvDescription.text = artifact.description // Description of artifact
            Glide.with(context).load(artifact.pictureUrl.url).into(itemView.imgObject) // Load images
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.model_museum_object, parent, false))
    }

    override fun getItemCount(): Int = artifacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(artifacts[position])
}