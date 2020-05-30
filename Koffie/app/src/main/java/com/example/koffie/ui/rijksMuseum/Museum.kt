package com.example.koffie.ui.rijksMuseum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.koffie.R

class Museum : Fragment() {

    private lateinit var dashboardViewModel: MuseumViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(MuseumViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_museum, container, false)
        return root
    }
}
