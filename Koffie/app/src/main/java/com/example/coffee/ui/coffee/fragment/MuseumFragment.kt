package com.example.coffee.ui.coffee.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.model.museum_model.MuseumArtifact
import com.example.coffee.ui.coffee.adapter.MuseumAdapter
import com.example.coffee.ui.coffee.viewmodel.MuseumViewModel
import kotlinx.android.synthetic.main.fragment_museum.*


class MuseumFragment : Fragment() {

    private lateinit var dashboardViewModel: MuseumViewModel // ViewModel
    private var artifacts = ArrayList<MuseumArtifact>()  // Array of MuseumObjects
    private var museumAdapter = MuseumAdapter(artifacts) {nextList()} // Adapter instantiated with MuseumObjects
    private var startPageArtifacts = 1 // First page we get from the web

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
     * Init the view with an adapter
     */
    private fun initView() {
        // Recyclerview with vertical layout
        rvArtifacts.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL , false)
//        val mDividerItemDecoration = DividerItemDecoration(rvArtifacts.context, DividerItemDecoration.VERTICAL)
        rvArtifacts.addItemDecoration(DividerItemDecoration(rvArtifacts.context, DividerItemDecoration.VERTICAL))

        rvArtifacts.adapter = museumAdapter // Set adapter of recyclerview
        dashboardViewModel.getMuseumObjects(startPageArtifacts)
    }

    /**
     * Get the next items.
     */
    private fun nextList(){
        view?.findViewById<RelativeLayout>(R.id.loadingPanel)?.visibility = View.VISIBLE
        dashboardViewModel.getMuseumObjects(++startPageArtifacts)
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


}

