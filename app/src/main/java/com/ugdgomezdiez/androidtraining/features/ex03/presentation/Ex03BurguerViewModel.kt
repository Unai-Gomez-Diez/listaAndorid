package com.ugdgomezdiez.androidtraining.features.ex03.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.features.ex03.domain.Burguer
import com.ugdgomezdiez.androidtraining.features.ex03.domain.GetBurguerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex03BurguerViewModel(private val getBurguerUseCase: GetBurguerUseCase): ViewModel() {

    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel> = _uiModel

    fun loadBurguer(){
        _uiModel.value = UiModel(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {  }
    }


            data class UiModel(
                val isLoading: Boolean= false,
                val errorApp: ErrorApp?=null,
                val burguer: Burguer?=null
            )
}