package com.example.domain.usecase

import com.example.domain.models.DoorsResponse
import com.example.domain.repository.Repository

class GetDoorsUseCase(private val repository: Repository) {
    suspend operator fun invoke(): DoorsResponse {
        return repository.getDoorsApi()
    }
}