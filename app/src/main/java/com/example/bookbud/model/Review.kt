package com.example.bookbud.model

data class Review(
    val id: String,
    val userId: String,
    val userName: String,
    val rating: Float,
    val comment: String,
    val date: String
) 