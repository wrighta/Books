package com.example.books.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.books.data.BookRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = BookRepository(app)
    val booksResponse = dataRepo.booksResponse
}


