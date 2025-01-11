package com.example.bookbud.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val description: String,
    val imageUrl: String?,
    val isSaved: Boolean,
    val reviews: List<Review>
)

data class Review(
    val id: String,
    val userId: String,
    val rating: Int,
    val comment: String,
    val timestamp: Long
) 