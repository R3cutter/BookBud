package com.example.bookbud.api

import com.example.bookbud.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("volumes")
    suspend fun getRandomBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BooksResponse

    @GET("volumes/{volumeId}")
    suspend fun getBookById(
        @Path("volumeId") volumeId: String,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BookItem
}

data class BooksResponse(
    val items: List<BookItem> = emptyList()
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>? = null,
    val description: String? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
) 