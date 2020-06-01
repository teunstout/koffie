package com.example.coffee.ui.rijksMuseum

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffee.data.rijksMuseumApi.MuseumServiceRepository
import com.example.coffee.model.rijksMuseumObjects.MuseumObject
import com.example.coffee.model.rijksMuseumObjects.MuseumWebObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumViewModel : ViewModel() {
    private val museumServiceRepository: MuseumServiceRepository = MuseumServiceRepository()
    val artifacts = MutableLiveData<ArrayList<MuseumObject>>()

    fun getMuseumObjects(pageNumber: Int) = run {
        museumServiceRepository.getObjectsMuseum(pageNumber)
            .enqueue(object : Callback<MuseumWebObject> {

                override fun onResponse(call: Call<MuseumWebObject>, response: Response<MuseumWebObject>) {
                    Log.i("BODY_RESPONSE", response.body().toString())
                    if (response.isSuccessful) response.body()?.let {
                        artifacts.value = response.body()?.artObjects as ArrayList<MuseumObject>
                    } else Log.e("GET_DATA_ERROR", response.errorBody().toString())
                }

                override fun onFailure(call: Call<MuseumWebObject>, t: Throwable) {
                    Log.e("GET_DATA_FAILURE", t.toString())
                }

            })
    }
}