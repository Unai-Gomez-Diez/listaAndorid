package com.ugdgomezdiez.androidtraining.features.ex01.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex01.domain.GetUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.DeleteUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex01FormularioViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
    ) :ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            saveUserUseCase(user).fold(
                { responseError(it) },
                { responseSuccess(it) }
            )
        }

    }

    fun loadUser(){
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase().fold(
                { responseError(it) },
                { responseGetUserSuccess(it) }
            )
        }
    }

    fun deleteUser(userId:String){
        deleteUserUseCase.invoke(userId).fold(
            {responseError(it)},
            {responseSuccess(it)}
        )

    }

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp=errorApp, isLoading = false))
    }
    private fun responseSuccess(isOk: Boolean){

    }

    private fun responseGetUserSuccess(user: List<User>) {
        _uiState.postValue(UiState(user = user))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: List<User>? = null
    )

}