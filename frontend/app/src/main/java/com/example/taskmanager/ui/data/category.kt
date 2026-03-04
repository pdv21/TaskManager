package com.example.taskmanager.ui.data

data class category(
    val id: Int,
    val name: String
)

val Category = listOf(
    category(1, "Design"),
    category(2, "Meeting"),
    category(3, "Coding"),
    category(4, "Testing"),
    category(5, "Quick Call"),
    category(6, "BDE")
)
