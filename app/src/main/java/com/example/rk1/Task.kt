package com.example.rk1

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val deadline: String,
    val priority: String,
    val isCompleted: Boolean = false
)