package com.example.koffie.ui.rijksMuseum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koffie.R
import com.example.koffie.model.MuseumObject
import kotlinx.android.synthetic.main.model_museum_object.view.*

class MuseumAdapter(private val artifacts: List<MuseumObject>) :
    RecyclerView.Adapter<MuseumAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(artifact: MuseumObject) {
            itemView.tvTitle.text = artifact.title
            itemView.tvCreator.text = artifact.creator
            itemView.tvDescription.text = artifact.creator
            Glide.with(context).load(artifact.pictureUrl).into(itemView.imgObject)
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