package com.example.plantwebapp.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.plantwebapp.LOG_TAG
import com.example.plantwebapp.WEB_SERVICE_URL
import com.example.plantwebapp.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PlantRepository (val app: Application){

    // Anything that observes this live data will see when there's changes - the mainFragment will observe
    val plantData = MutableLiveData<List<Plant>>()

    // This creates a custom type that I will use to parse json content
    private val listType = Types.newParameterizedType(
        List::class.java, Plant::class.java
    )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
            //   getPlantData()
            //    Log.i(LOG_TAG, "Network available: ${networkAvailable()}")
        }
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(PlantService::class.java)
            val serviceData = service.getPlantData().body() ?: emptyList()
            plantData.postValue(serviceData)
            Log.i("HERE", plantData.toString())
        }
    }


    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }


//    fun getPlantData() {
//        val text = FileHelper.getTextFromAssets(app, "plants.json")
//        // moshi is an instance of the Moshi library
//        val moshi = Moshi.Builder().build()
//
//        // the adapter is similar to the adapter we use for RecyclerView
//        // it is a bridge between an android component and a data source (eg. we have a list of Plant objects)
//        val adapter: JsonAdapter<List<Plant>> =
//            moshi.adapter(listType)
//
//        // convert the text from the json file into a list of plant objects and assign it to the mutableLiveData plantList
//         plantData.value = adapter.fromJson(text) ?: emptyList()
//
//        // loop through each element in the list of plants,
////        for (plant in plantData ?: emptyList()) {
////            Log.i(
////                LOG_TAG,
////                "${plant.plantTitle} , ${plant.description}")
////        }
//    }


}