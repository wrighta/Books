package com.example.books.data

class ImageLinks (
    val smallThumbnail : String,
    val thumbnail : String
)
{
    val smallImageUrl
    get() = smallThumbnail

    val thumbnailImageUrl
    get() = thumbnail
}