package com.example.koffie.ui.rijksMuseum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koffie.data.rijksMuseumApi.MuseumServiceRepository
import com.example.koffie.model.MuseumObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumViewModel : ViewModel() {
    private val museumServiceRepository: MuseumServiceRepository = MuseumServiceRepository()
    // livedata
    // error

    fun getMuseumObjects() = run {
        museumServiceRepository.getObjectsMuseum(1)
            .enqueue(object : Callback<List<MuseumObject>> {
                override fun onFailure(call: Call<List<MuseumObject>>, t: Throwable) {
                    return
                }

                override fun onResponse(
                    call: Call<List<MuseumObject>>,
                    response: Response<List<MuseumObject>>
                ) {
                    return
                }

            })
    }
}