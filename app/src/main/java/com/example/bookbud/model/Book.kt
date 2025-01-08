package com.example.bookbud.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverUrl: String,
    val description: String,
    val publishYear: Int,
    val genre: String,
    val rating: Float,
    val isSaved: Boolean = false,
    val reviews: List<Review> = emptyList()
)

data class Review(
    val id: String,
    val userId: String,
    val userName: String,
    val rating: Float,
    val comment: String,
    val date: String
) 