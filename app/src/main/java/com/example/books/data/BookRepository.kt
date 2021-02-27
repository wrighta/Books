package com.example.books.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.books.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BookRepository  (val app: Application){

    // Anything that observes this live data will see when there's changes - the mainFragment will observe
   // val plantData = MutableLiveData<List<Plant>>()

    // Anything that observes this live data will see when there's changes - the mainFragment will observe
    val booksResponse = MutableLiveData<BooksResponse>()

    //val userLogin = UserLogin("", "admin@selfmade.ie", "secret", "")

    init {
        // Run this in the background.
        CoroutineScope(Dispatchers.IO).launch {
            callLoginService()

        }
    }

    @WorkerThread
    suspend fun callLoginService() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            // Ask retrofit to create an implementation of the API endpoints (implements the functions in PlantService interface, these functional are the contact points with the API)
            val service = retrofit.create(BookService::class.java)


            // getPlantData() is defined in the WebService interface, Retrofit will have implemented this function for you
            val res  = service.simpleSearchBooks("Harry%20Potter")
            booksResponse.postValue(res)
            Log.i("HERE", booksResponse.toString())
        }
    }


    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

}