package com.example.books.data

data class BooksResponse (
    val totalItems: Int,
    val items: List<BookResult>)