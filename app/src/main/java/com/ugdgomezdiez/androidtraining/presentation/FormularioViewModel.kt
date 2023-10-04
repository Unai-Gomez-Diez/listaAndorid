package com.ugdgomezdiez.androidtraining.presentation

import androidx.lifecycle.ViewModel
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase

class FormularioViewModel(private val saveUserUseCase: SaveUserUseCase) :ViewModel() {

    fun saveUser(name: String, surname: String){
        saveUserUseCase(input ).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    private fun responseError(errorApp: ErrorApp){

    }
    private fun responseSuccess(isOk: Boolean){

    }

}