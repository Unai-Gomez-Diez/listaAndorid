package com.ugdgomezdiez.androidtraining.features.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.extensions.hide
import com.ugdgomezdiez.androidtraining.app.extensions.visible
import com.ugdgomezdiez.androidtraining.databinding.ActivityFormularioBinding
import com.ugdgomezdiez.androidtraining.features.ex01.data.UserDataRepository
import com.ugdgomezdiez.androidtraining.features.ex01.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex01.domain.GetUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User


class Ex01MainActivity : AppCompatActivity() {


   lateinit var binding: ActivityFormularioBinding

    //val viewModels: FormularioViewModel by viewModels()
    val viewModel: Ex01FormularioViewModel by lazy {
        Ex01FormularioViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupView()
        setupObservers()
        viewModel.loadUser()
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
            viewModel.saveUser(getNameInput(),getSurnameInput(), getAgeInput())



        }
        binding.actionSave.setOnClickListener {
            //lo que sea
        }
        findViewById<ViewGroup>(R.id.row_2).findViewById<TextView>(R.id.name)
        findViewById<ViewGroup>(R.id.row_3).findViewById<TextView>(R.id.name)
        findViewById<ViewGroup>(R.id.row_4).findViewById<TextView>(R.id.name)


    }

   private fun getNameInput(): String=
       findViewById<EditText>(R.id.input_name).text.toString()
    //primero busca el elemento, luego saca la propiedad con text y luego lo pasa a String

   private fun getSurnameInput(): String=
        findViewById<EditText>(R.id.input_surname).text.toString()

    private fun getAgeInput(): String =
        findViewById<EditText>(R.id.input_age).text.toString()



    private fun setupObservers() {
        val observer = Observer<Ex01FormularioViewModel.UiState> {
            //Código al notificar el observador
            if (it.isLoading) {
                //Muestro el loading
                showLoading()
            } else {
                //Oculto el loading
                hideLoading()
            }
            it.errorApp?.let {
                showError(it)
            }
            it.user?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showError(error: ErrorApp) {
        binding.viewError.layoutError.visible()
        binding.layoutForm.hide()
        when (error) {
            ErrorApp.UnknowError -> binding.viewError.messageError.text =
                getString(R.string.label_unknown_error)
        }
    }

    private fun showLoading() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading() {
        binding.skeletonLayout.showOriginal()
        binding.layoutForm.visible()
    }

    private fun bindData(user: User) {
        setNameInput(user.username)
        setSurnameInput(user.surname)
        setAgeInput(user.age)

    }

    private fun setNameInput(name: String) {
        findViewById<EditText>(R.id.name).setText(name)
    }

    private fun setSurnameInput(surname: String) {
        findViewById<EditText>(R.id.surname).setText(surname)
    }

    private fun setAgeInput(age: String) {
        findViewById<EditText>(R.id.input_age).setText(age)
        findViewById<EditText>(R.id.input_age).visibility = View.VISIBLE
        findViewById<EditText>(R.id.input_age).visibility = View.GONE
        findViewById<EditText>(R.id.input_age).visibility = View.INVISIBLE
    }

    fun bindData(users: List<User>) {
        if (users.size == 1) {
            findViewById<ViewGroup>(R.id.row_1).apply {
                findViewById<TextView>(R.id.name).text = users.get(0).username
                findViewById<TextView>(R.id.surname).text = users.get(0).surname
                findViewById<TextView>(R.id.action_clean).setOnClickListener {
                    //viewModel.deleteUser(users.get(0).id)
                }
            }
            binding.apply {
                row1.name.text
                row2.name.text
            }
        }
        if (users.size == 2) {

        }
    }

}