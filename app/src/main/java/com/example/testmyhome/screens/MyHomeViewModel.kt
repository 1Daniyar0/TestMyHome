package com.example.testmyhome.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Camera
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsResponse
import com.example.domain.usecase.GetCamerasListUseCase
import com.example.domain.usecase.GetDoorsUseCase
import kotlinx.coroutines.launch

class MyHomeViewModel(
    private val getCamerasListUseCase: GetCamerasListUseCase,
    private val getDoorsUseCase: GetDoorsUseCase,
): ViewModel() {
    private val _camerasLiveData = MutableLiveData<List<Camera>>()
    private val _doorsLiveData = MutableLiveData<DoorsResponse>()

    val doorsLiveData: LiveData<DoorsResponse> = _doorsLiveData
    val camerasLiveData: LiveData<List<Camera>> = _camerasLiveData

    fun getCameras(){
        viewModelScope.launch {
            try {
                val response = getCamerasListUseCase()
                _camerasLiveData.postValue(response!!)
            }
            catch (e: Exception){
                Log.e("ViewModel Error",e.toString())
            }
        }
    }

}