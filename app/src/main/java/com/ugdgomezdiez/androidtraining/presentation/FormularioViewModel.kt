package com.ugdgomezdiez.androidtraining.presentation

import androidx.lifecycle.ViewModel
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.domain.Input
import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase

class FormularioViewModel(private val saveUserUseCase: SaveUserUseCase) :ViewModel() {

    fun saveUser(name: String, surname: String, date: String){
        saveUserUseCase(Input(name, surname, date)).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    private fun responseError(errorApp: ErrorApp){

    }
    private fun responseSuccess(isOk: Boolean){

    }

}