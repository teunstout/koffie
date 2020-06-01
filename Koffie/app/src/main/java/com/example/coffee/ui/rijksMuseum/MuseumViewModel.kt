package com.example.coffee.ui.rijksMuseum

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffee.data.rijksMuseumApi.MuseumServiceRepository
import com.example.coffee.model.rijksMuseumObjects.MuseumObject
import com.example.coffee.model.rijksMuseumObjects.MuseumWebObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumViewModel : ViewModel() {
    private val museumServiceRepository: MuseumServiceRepository = MuseumServiceRepository()
    val artifacts = MutableLiveData<ArrayList<MuseumObject>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getMuseumObjects(pageNumber: Int) = coroutineScope.launch {
        artifacts.value =
            withContext(Dispatchers.IO) { museumServiceRepository.getObjectsMuseum(pageNumber).artObjects as ArrayList<MuseumObject> }
    }

//        run {
//        museumServiceRepository.getObjectsMuseum(pageNumber)
//            .enqueue(object : Callback<MuseumWebObject> {
//
//                override fun onResponse(call: Call<MuseumWebObject>, response: Response<MuseumWebObject>) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            artifacts.value = response.body()?.artObjects as ArrayList<MuseumObject>
//                        }
//                    } else Log.e("GET_DATA_ERROR", response.errorBody().toString())
//                }
//
//                override fun onFailure(call: Call<MuseumWebObject>, t: Throwable) {
//                    Log.e("GET_DATA_FAILURE", t.toString())
//                }
//            })
//    }
}