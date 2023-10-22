package com.ugdgomezdiez.androidtraining.features.ex02.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User
import com.ugdgomezdiez.androidtraining.features.ex01.presentation.Ex01FormularioViewModel
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.GetDogUseCase
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date

class Ex02PerroViewModel(private val getDogUseCase: GetDogUseCase,
                         private val saveDogUseCase: SaveDogUseCase): ViewModel() {

    private val _uiState = MutableLiveData<Ex02PerroViewModel.UiState>()
    val uiState: LiveData<Ex02PerroViewModel.UiState> = _uiState



    fun loadDog(){
        _uiState.value =UiState(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            saveDogUseCase()
            getDogUseCase().fold(
                { responseError(it) },
                { responseGetDogSuccess(it) }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp))
    }

    private fun responseGetDogSuccess(dogModel: DogModel) {
        _uiState.postValue(UiState(dogModel= dogModel))
    }
    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val dogModel: DogModel? = null
    )
}