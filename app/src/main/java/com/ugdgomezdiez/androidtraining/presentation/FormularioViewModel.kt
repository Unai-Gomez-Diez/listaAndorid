package com.ugdgomezdiez.androidtraining.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.domain.GetUserUseCase
import com.ugdgomezdiez.androidtraining.domain.ResetUserUseCase
import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormularioViewModel(
        private val saveUserUseCase: SaveUserUseCase,
        private val getUserUseCase: GetUserUseCase,
        private val resetUserUseCase: ResetUserUseCase
    ) :ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(name: String, surname: String, date: String){
        saveUserUseCase(SaveUserUseCase.Input(name, surname, date)).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    fun loadUser(){
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase().fold(
                { responseError(it) },
                { responseGetUserSuccess(it) }
            )
        }
    }

    fun resetUser(){
        resetUserUseCase(SaveUserUseCase.Input("","","")).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    private fun responseError(errorApp: ErrorApp){

    }
    private fun responseSuccess(isOk: Boolean){

    }

    private fun responseGetUserSuccess(user: User) {
        _uiState.postValue(UiState(user = user))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: User? = null
    )

}