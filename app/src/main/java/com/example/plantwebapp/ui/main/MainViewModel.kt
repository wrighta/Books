package com.example.plantwebapp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.plantwebapp.LOG_TAG
import com.example.plantwebapp.data.Plant
import com.example.plantwebapp.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MainViewModel(app: Application) : AndroidViewModel(app) {

    // This creates a custom type that I will use to parse json content
    private val listType = Types.newParameterizedType(
        List::class.java, Plant::class.java
    )

    init {

        val text = FileHelper.getTextFromAssets(app, "plants.json")
        //    Log.i(LOG_TAG, text)
        parseText(text)
    }


        fun parseText(text: String) {
            // moshi is an instance of the Moshi libary
            val moshi = Moshi.Builder().build()

            // the adapter is similar to the adapter we use for RecyclerView
            // it is a bridge between an android component and a data source (eg. we have a list of Plant objects)
            val adapter: JsonAdapter<List<Plant>> =
                moshi.adapter(listType)

            // convert the text from the json file into a list of plant objects
            val plantData = adapter.fromJson(text)

            // loop through each element in the list of plants,
            for (plant in plantData ?: emptyList()) {
                Log.i(LOG_TAG,
                    "${plant.plantTitle} , ${plant.description}")
            }
        }

    }

