package com.example.plantwebapp.data

import retrofit2.Response
import retrofit2.http.GET

interface PlantService {
    @GET("plants.json")
    suspend fun getPlantData() : Response<List<Plant>>
}

//interface PlantService {
//    @GET("/feed/monster_data.json")
//    suspend fun getPlantData(): Response<List<Plant>>
//}