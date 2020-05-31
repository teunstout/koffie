package com.example.coffee.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coffee.R

class Statistics : Fragment() {

    private lateinit var notificationsViewModel: StatisticsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(StatisticsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }
}
