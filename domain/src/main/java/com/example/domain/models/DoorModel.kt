package com.example.domain.models

data class DoorResponse(
    val success: Boolean,
    val data: List<Door>
)

data class Door(
    val name: String,
    val room: String,
    val id: Int,
    val favorites: Boolean
)