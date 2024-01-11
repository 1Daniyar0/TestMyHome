package com.example.testmyhome.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Camera
import com.example.domain.models.CamerasResponse
import com.example.domain.models.Door
import com.example.domain.models.DoorsResponse
import com.example.domain.usecase.GetCamerasListUseCase
import com.example.domain.usecase.GetDoorsUseCase
import com.example.domain.usecase.UpdateCameraDbByIdUseCase
import kotlinx.coroutines.launch

class MyHomeViewModel(
    private val getCamerasListUseCase: GetCamerasListUseCase,
    private val getDoorsUseCase: GetDoorsUseCase,
    private val updateCameraDbByIdUseCase: UpdateCameraDbByIdUseCase
): ViewModel() {
    private val _camerasLiveData = MutableLiveData<List<Camera>>()
    private val _doorsLiveData = MutableLiveData<List<Door>>()

    val camerasLiveData: LiveData<List<Camera>> = _camerasLiveData
    val doorsLiveData: LiveData<List<Door>> = _doorsLiveData

    fun getCameras(){
        viewModelScope.launch {
            try {
                val response = getCamerasListUseCase()
                _camerasLiveData.postValue(response!!)
            }
            catch (e: Exception){
                Log.e("ViewModel Camera Error",e.toString())
            }
        }
    }

    fun getDoors(){
        viewModelScope.launch {
            try {
                val response = getDoorsUseCase()
                _doorsLiveData.postValue(response!!)
            }
            catch (e: Exception){
                Log.e("ViewModel Doors Error",e.toString())
            }
        }
    }

    fun updateCameraDb(camera: Camera){
        viewModelScope.launch {
            try {
                updateCameraDbByIdUseCase(camera)
            }
            catch (e: Exception){
                Log.e("ViewModel Update Error",e.toString())
            }
        }

    }

}