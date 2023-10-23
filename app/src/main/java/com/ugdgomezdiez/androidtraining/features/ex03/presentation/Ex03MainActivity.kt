package com.ugdgomezdiez.androidtraining.features.ex03.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.faltenreich.skeletonlayout.Skeleton
import com.google.android.material.snackbar.Snackbar
import com.iesam.kotlintrainning.left
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.extensions.setUrl
import com.ugdgomezdiez.androidtraining.app.serialization.GsonSerialization
import com.ugdgomezdiez.androidtraining.databinding.Activity02burguerBinding
import com.ugdgomezdiez.androidtraining.databinding.ActivityFormularioBinding
import com.ugdgomezdiez.androidtraining.features.ex03.data.BurguerDataRepository
import com.ugdgomezdiez.androidtraining.features.ex03.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex03.data.remote.ApiRemoteDataSource
import com.ugdgomezdiez.androidtraining.features.ex03.domain.Burguer
import com.ugdgomezdiez.androidtraining.features.ex03.domain.GetBurguerUseCase

class Ex03MainActivity: AppCompatActivity() {

    private lateinit var binding: Activity02burguerBinding
    private val viewModel: Ex03BurguerViewModel by lazy {
        Ex03BurguerViewModel(
            GetBurguerUseCase(
                BurguerDataRepository(
                    XmlLocalDataSource(
                        this,
                        GsonSerialization()
                    ), ApiRemoteDataSource()
                )
            )
        )
    }

    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObserver()
        viewModel.loadBurguer()
    }

    private fun setupBinding(){
        binding = Activity02burguerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView(){
        skeleton = binding.skeletonLayout
    }

    private fun setupObserver(){
        val observer = Observer<Ex03BurguerViewModel.UiModel>{
            if(it.isLoading){
                showLoading()
            }else{
                hideLoading()
            }

            it.errorApp?.let {
                showError(it)
            }

            it.burguer?.let {
                bindData(it)
            }
        }
        viewModel.uiModel.observe(this, observer)
    }

    private fun showError(error: ErrorApp) {
        Snackbar.make(
            binding.root,
            getString(R.string.label_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }
    private fun showLoading() {
        skeleton.showSkeleton()
    }

    private fun hideLoading() {
        skeleton.showOriginal()
    }

    private fun bindData(burguer: Burguer){
        binding.apply {
            title.text=burguer.title
            imgburguer.setUrl(burguer.urlImage)
            discount.text=burguer.discount
            percent.text=burguer.rate
            time.text=burguer.eta
        }
    }
}