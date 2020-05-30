package com.example.koffie.ui.rijksMuseum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.koffie.R
import kotlinx.android.synthetic.main.fragment_museum.*

class Museum : Fragment() {

    private lateinit var dashboardViewModel: MuseumViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(MuseumViewModel::class.java)
        return inflater.inflate(R.layout.fragment_museum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            dashboardViewModel.getMuseumObjects()
            Toast.makeText(this.context, "gelukt", Toast.LENGTH_SHORT).show()
        }
    }
}
