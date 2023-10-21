package com.ugdgomezdiez.androidtraining.features.ex02.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.databinding.ActivityEx03GlideBinding
import com.ugdgomezdiez.androidtraining.databinding.ActivityPerroBinding
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogDataRepository
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.ApiMockRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.ApiRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.GetDogUseCase
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase
import java.text.SimpleDateFormat
import java.util.Date

class Ex02MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityPerroBinding

    val viewModel: Ex02PerroViewModel by lazy {
        Ex02PerroViewModel(
            GetDogUseCase(DogDataRepository(XmlLocalDataSource(this), ApiRemoteDataSource())))
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perro)
        setupView()
    }

    private fun setupView(){
        viewModel.loadDog()
        setupObservers()
    }
    private fun setupObservers() {
        val observer = Observer<Ex02PerroViewModel.UiState> {
            //Código al notificar el observador
            if(it.isLoading){
                Snackbar.make(binding.root, "Cargando....", Snackbar.LENGTH_SHORT).show()
            }else{

            }

            it.dogModel?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }







    private fun bindData(dogModel: DogModel){
        setNameDogInput(dogModel.name)
        setDescriptionDogInput(dogModel.short_description)
        setSexDogInput(dogModel.sex)
        setDatBornDogInput(dogModel.date_birth)

    }
    private fun setNameDogInput(name: String) {
        findViewById<TextView>(R.id.nombre_dog).setText(name)
    }

    private fun setDescriptionDogInput(description: String) {
        findViewById<TextView>(R.id.descripcion_dog).setText(description)
    }

    private fun setSexDogInput(sex: String) {
        findViewById<TextView>(R.id.sex_dog).setText(sex)
    }


    private fun setDatBornDogInput(dateBorn: String){
        findViewById<TextView>(R.id.dateborn_dog).setText(dateBorn)
    }

    private fun setUrlImageInput(urlImage: String){
        findViewById<TextView>(R.id.imageUrl).setText(urlImage)
    }
}