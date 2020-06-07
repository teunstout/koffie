package com.example.coffee.ui.coffee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffee.data.rijksMuseumApi.MuseumServiceRepository
import com.example.coffee.model.museum_model.MuseumArtifact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MuseumViewModel : ViewModel() {
    private val museumServiceRepository: MuseumServiceRepository = MuseumServiceRepository()
    val artifacts = MutableLiveData<ArrayList<MuseumArtifact>>()

    // Get the museum objects for page number.
    fun getMuseumObjects(pageNumber: Int) = CoroutineScope(Dispatchers.Main).launch {
        artifacts.value =
            withContext(Dispatchers.IO) { museumServiceRepository.getObjectsMuseum(pageNumber).artArtifacts as ArrayList<MuseumArtifact> }
    }
}