package com.example.testmyhome.di

import com.example.domain.models.CamerasRealmModel
import com.example.data.remote.ApiClient.client
import com.example.data.repositoryImpl.ApiRepositoryImpl
import com.example.data.repositoryImpl.RealmRepositoryImpl
import com.example.domain.repository.ApiRepository
import com.example.domain.repository.RealmRepository
import com.example.domain.usecase.AddCamerasDbUseCase
import com.example.domain.usecase.GetCamerasUseCase
import com.example.domain.usecase.GetDoorsUseCase
import com.example.testmyhome.screens.MyHomeViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    factory { GetCamerasUseCase(get()) }
    factory { GetDoorsUseCase(get()) }
    factory { AddCamerasDbUseCase(get()) }
    single<ApiRepository>{ ApiRepositoryImpl() }
    single<RealmRepository> { RealmRepositoryImpl() }
}

val viewModelModule = module {
    viewModel {MyHomeViewModel(get(),get(),get())}
}

val netModule = module{
    factory {client}
}






