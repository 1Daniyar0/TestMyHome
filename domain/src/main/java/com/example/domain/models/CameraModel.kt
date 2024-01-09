package com.example.domain.models

data class CamerasResponse(
    val success: Boolean? = true,
    val data: CamerasData
)

data class CamerasData(
    val room: List<String>,
    val cameras: List<Camera>
)

data class Camera(
    val name: String,
    val snapshot: String,
    val room: String,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)

