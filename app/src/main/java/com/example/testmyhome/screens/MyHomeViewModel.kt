package com.example.testmyhome.screens

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Camera
import com.example.domain.models.CamerasRealmModel
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsResponse
import com.example.domain.usecase.AddCamerasDbUseCase
import com.example.domain.usecase.GetCamerasUseCase
import com.example.domain.usecase.GetDoorsUseCase
import kotlinx.coroutines.launch

class MyHomeViewModel(
    private val getCamerasUseCase: GetCamerasUseCase,
    private val getDoorsUseCase: GetDoorsUseCase,
    private val addCamerasDbUseCase: AddCamerasDbUseCase
): ViewModel() {
    private val _camerasLiveData = MutableLiveData<CamerasResponse>()
    private val _doorsLiveData = MutableLiveData<DoorsResponse>()
    private val _camerasDbLiveData = MutableLiveData<CamerasRealmModel>()

    val doorsLiveData: LiveData<DoorsResponse> = _doorsLiveData
    val camerasLiveData: LiveData<CamerasResponse> = _camerasLiveData
    val camerasDbLiveData: LiveData<CamerasRealmModel> = _camerasDbLiveData

    fun getCameras(){
        viewModelScope.launch {
            try {
                val response = getCamerasUseCase()
                _camerasLiveData.postValue(response)
            }
            catch (e: Exception){
                Log.e("Response Error",e.toString())
            }
        }
    }

    fun addCamerasIntoDb(data: List<Camera>){
        viewModelScope.launch {
            try {
                addCamerasDbUseCase(data)
            }
            catch (e: Exception){
                Log.e("DataBase ViewModel Error",e.toString())
            }
        }
    }

}