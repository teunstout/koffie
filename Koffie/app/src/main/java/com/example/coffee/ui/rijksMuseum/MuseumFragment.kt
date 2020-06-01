package com.example.coffee.ui.rijksMuseum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.rijksMuseumObjects.MuseumObject
import kotlinx.android.synthetic.main.fragment_museum.*

class MuseumFragment : Fragment() {

    private lateinit var dashboardViewModel: MuseumViewModel // ViewModel
    private var artifacts = ArrayList<MuseumObject>()  // Array of MuseumObjects
    private var museumAdapter = MuseumAdapter(artifacts) // Adapter instantiated with MuseumObjects

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel = ViewModelProvider(this).get(MuseumViewModel::class.java) // ViewModel of museum
        return inflater.inflate(R.layout.fragment_museum, container, false) // Inflate view with museum fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        listenLiveData()
    }

    /**
     * Observe the data from the ViewModel
     */
    private fun listenLiveData() {
        dashboardViewModel.artifacts.observe(viewLifecycleOwner, Observer {
            artifacts.addAll(it) // Add all data
            view?.findViewById<RelativeLayout>(R.id.loadingPanel)?.visibility = View.GONE
            museumAdapter.notifyDataSetChanged() // Notify adapter of change
        })
    }

    /**
     * Init the view with an adapter
     */
    private fun initView() {
        // Recyclerview with vertical layout
        rvArtifacts.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL , false)
        rvArtifacts.adapter = museumAdapter // Set adapter of recyclerview
        dashboardViewModel.getMuseumObjects(1)
    }
}

