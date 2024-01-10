package com.example.testmyhome.screens

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsResponse
import com.example.domain.usecase.GetCamerasUseCase
import com.example.domain.usecase.GetDoorsUseCase
import kotlinx.coroutines.launch

class MyHomeViewModel(
    private val getCamerasUseCase: GetCamerasUseCase,
    private val getDoorsUseCase: GetDoorsUseCase
): ViewModel() {
    private val _camerasLiveData = MutableLiveData<CamerasResponse>()
    private val _doorsLiveData = MutableLiveData<DoorsResponse>()

    val doorsLiveData: LiveData<DoorsResponse> = _doorsLiveData
    val camerasLiveData: LiveData<CamerasResponse> = _camerasLiveData

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
}