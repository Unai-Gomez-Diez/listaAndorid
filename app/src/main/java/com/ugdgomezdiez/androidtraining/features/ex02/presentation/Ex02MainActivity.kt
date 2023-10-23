package com.ugdgomezdiez.androidtraining.features.ex02.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.faltenreich.skeletonlayout.Skeleton
import com.google.android.material.snackbar.Snackbar
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.app.extensions.setUrl
import com.ugdgomezdiez.androidtraining.databinding.ActivityPerroBinding
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogDataRepository
import com.ugdgomezdiez.androidtraining.features.ex02.data.DogModel
import com.ugdgomezdiez.androidtraining.features.ex02.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.data.remote.ApiRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex02.domain.Dog
import com.ugdgomezdiez.androidtraining.features.ex02.domain.DogRepository
import com.ugdgomezdiez.androidtraining.features.ex02.domain.GetDogUseCase
import com.ugdgomezdiez.androidtraining.features.ex02.domain.SaveDogUseCase

class Ex02MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityPerroBinding
    private lateinit var skeleton: Skeleton
    val viewModel: Ex02PerroViewModel by lazy {
        Ex02PerroViewModel(
            GetDogUseCase(DogDataRepository(XmlLocalDataSource(this), ApiRemoteDataSource())),
            SaveDogUseCase(DogDataRepository(XmlLocalDataSource(this),ApiRemoteDataSource()))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perro)
        setupBinding()
        setupView()

        setupObservers()
        viewModel.loadDog()
    }

    private fun setupView(){
        skeleton=binding.skeletonLayout

    }
    private fun setupObservers() {
        val observer = Observer<Ex02PerroViewModel.UiState> {
            //Código al notificar el observador
            if(it.isLoading){
                showLoading()
            //Snackbar.make(binding.root, "Cargando....", Snackbar.LENGTH_SHORT).show()
            }else{
                hideLoading()
            }

            it.dog?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

   /* private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }*/

    private fun showLoading(){
        skeleton.showSkeleton()
    }

    private fun hideLoading(){
        skeleton.showOriginal()
    }

    private fun setupBinding(){
        binding = ActivityPerroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




    private fun bindData(dog: Dog){
        setUrlImageInput(dog.urlimage)
        setNameDogInput(dog.name)
        setDescriptionDogInput(dog.description)
        setSexDogInput(dog.sex)
        setDatBornDogInput(dog.dateBorn)

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
        findViewById<ImageView>(R.id.imageUrl).setUrl(urlImage)
    }
}