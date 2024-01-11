package com.example.testmyhome.di

import com.example.data.remote.ApiClient.client
import com.example.data.repositoryImpl.CamerasRepositoryImpl
import com.example.data.repositoryImpl.DoorsRepositoryImpl
import com.example.domain.repository.CamerasRepository
import com.example.domain.repository.DoorsRepository
import com.example.domain.usecase.GetCamerasListUseCase
import com.example.domain.usecase.GetDoorsUseCase
import com.example.domain.usecase.UpdateCameraDbByIdUseCase
import com.example.domain.usecase.UpdateDoorDbByIdUseCase
import com.example.testmyhome.screens.MyHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    factory { GetCamerasListUseCase(get()) }
    factory { GetDoorsUseCase(get()) }
    factory { UpdateCameraDbByIdUseCase(get()) }
    factory { UpdateDoorDbByIdUseCase(get()) }
    single<CamerasRepository> { CamerasRepositoryImpl() }
    single<DoorsRepository> { DoorsRepositoryImpl() }
}

val viewModelModule = module {
    viewModel {MyHomeViewModel(get(),get(),get(),get())}
}

val netModule = module{
    factory {client}
}






