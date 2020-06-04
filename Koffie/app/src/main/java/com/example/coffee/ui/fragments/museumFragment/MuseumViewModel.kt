package com.example.coffee.ui.fragments.museumFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffee.data.rijksMuseumApi.MuseumServiceRepository
import com.example.coffee.model.rijksMuseumObjects.MuseumObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MuseumViewModel : ViewModel() {
    private val museumServiceRepository: MuseumServiceRepository = MuseumServiceRepository()
    val artifacts = MutableLiveData<ArrayList<MuseumObject>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getMuseumObjects(pageNumber: Int) = coroutineScope.launch {
        artifacts.value =
            withContext(Dispatchers.IO) { museumServiceRepository.getObjectsMuseum(pageNumber).artObjects as ArrayList<MuseumObject> }
    }
}