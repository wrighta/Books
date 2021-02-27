package com.example.books.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.*


public interface BookService {
    // suspend keyword - means this needs to be run in the background, Coroutines looks after this for you.

    @GET("//books/v1/volumes?maxResults=40")
    fun searchBooks(@Query("startIndex") startIndex: Int,
                    @Query("q") searchString: String,
                    @Query("key") apiKey: String
    ): BooksResponse

    @GET("/books/v1/volumes?maxResults=40")
    suspend fun simpleSearchBooks(
                    @Query("q") searchString: String

    ): BooksResponse


    @GET("volumes/{id}")
    fun getBook(
        @Path("id") id: String
    ): BooksResponse
}


//    @Headers("Content-Type: application/json")
//    @POST("login/")
//    suspend fun tryLogin(@Body userData : UserLogin) : UserLogin
//
//
//    @GET("jobs/")
//    suspend fun getJobs(@Header ("Authorization") token: String) : Response<List<Job>>


