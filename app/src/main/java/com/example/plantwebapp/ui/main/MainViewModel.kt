package com.example.plantwebapp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.plantwebapp.LOG_TAG
import com.example.plantwebapp.data.Plant
import com.example.plantwebapp.data.PlantRepository
import com.example.plantwebapp.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = PlantRepository(app)
    val plantData = dataRepo.plantData
}

