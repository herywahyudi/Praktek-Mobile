package com.example.Scanner

data class Response(
    val status: Int,
    val message: String,
    val data: DataMahasiswa? = null
)
