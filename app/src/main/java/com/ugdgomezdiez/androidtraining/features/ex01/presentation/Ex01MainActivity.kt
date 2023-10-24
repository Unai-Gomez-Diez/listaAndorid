package com.ugdgomezdiez.androidtraining.features.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.databinding.ActivityFormularioBinding
//import com.ugdgomezdiez.androidtraining.databinding.ActivityFormularioBinding
import com.ugdgomezdiez.androidtraining.features.ex01.data.UserDataRepository
import com.ugdgomezdiez.androidtraining.features.ex01.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex01.domain.GetUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.DeleteUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User

class Ex01MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityFormularioBinding

    //val viewModels: FormularioViewModel by viewModels()
    val viewModel: Ex01FormularioViewModel by lazy {
        Ex01FormularioViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            DeleteUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setContentView(R.layout.activity_formulario)
        setupView()
        viewModel.loadUser()
        setupObservers()


        //viewModel.loadUser()
    }

    private fun bindView(){
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }



    private fun setupView(){
        //poner uno para recuperar
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(User(0,getName(),getSurname()))
            viewModel.loadUser()

            findViewById<ViewGroup>(R.id.row_1).findViewById<TextView>(R.id.name)
        }

        val actionButton2 = findViewById<Button>(R.id.action_get)
        actionButton2.setOnClickListener {
            findViewById<EditText>(R.id.input_name).setText("")
            findViewById<EditText>(R.id.input_surname).setText("")
        }



    }

   private fun getName(): String=
       findViewById<EditText>(R.id.input_name).text.toString()
    //primero busca el elemento, luego saca la propiedad con text y luego lo pasa a String

   private fun getSurname(): String=
        findViewById<EditText>(R.id.input_surname).text.toString()



    private fun setupObservers() {
        val observer= Observer<Ex01FormViewModel.UiState>{
            if(it.isLoading){
                showLoading()
            }else{
                hideLoading()
            }
            it.errorApp?.let {
                showError(it)
            }
            it.user?.apply {
                bindData(this)
            }
        }
        viewModels.uiState.observe(this,observer)}

    private fun showError(error: ErrorApp) {
        binding.layoutError.visible()
        when(error){
            ErrorApp.UnknowError -> binding.messageError.text=
                getString(R.string.lable_unknown_error)
        }
    }

    private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading(){
        binding.skeletonLayout.showOriginal()
    }



     fun bindData(user: User) {
        setNameInput(user.username)
        setSurnameInput(user.surname)

    }

    private fun setNameInput(name: String) {
        findViewById<EditText>(R.id.name).setText(name)
    }

    private fun setSurnameInput(surname: String) {
        findViewById<EditText>(R.id.surname).setText(surname)
    }


}