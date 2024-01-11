package com.example.domain.usecase

import com.example.domain.models.Camera
import com.example.domain.repository.RealmRepository

class AddCamerasDbUseCase(private val realmRepository: RealmRepository) {
    suspend operator fun invoke(data: List<Camera>){
        realmRepository.addCamerasToRealm(data)
    }
}