package com.example.testmyhome.di

import com.example.data.remote.ApiClient.client
import com.example.data.repositoryImpl.CamerasRepositoryImpl
import com.example.domain.repository.CamerasRepository
import com.example.domain.usecase.GetCamerasListUseCase
import com.example.domain.usecase.GetDoorsUseCase
import com.example.testmyhome.screens.MyHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    factory { GetCamerasListUseCase(get()) }
    factory { GetDoorsUseCase(get()) }
    single<CamerasRepository> { CamerasRepositoryImpl() }
}

val viewModelModule = module {
    viewModel {MyHomeViewModel(get(),get())}
}

val netModule = module{
    factory {client}
}






