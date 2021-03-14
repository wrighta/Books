package com.example.books.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.books.data.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = BookRepository(app)
    var booksResponse = dataRepo.booksResponse

    fun getBooks(searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepo.searchBooks(searchQuery)
            booksResponse = dataRepo.booksResponse
        }
    }
}


