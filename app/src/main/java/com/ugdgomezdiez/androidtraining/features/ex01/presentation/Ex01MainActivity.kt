package com.ugdgomezdiez.androidtraining.features.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.features.ex01.data.UserDataRepository
import com.ugdgomezdiez.androidtraining.features.ex01.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.features.ex01.domain.GetUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.ResetUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.SaveUserUseCase
import com.ugdgomezdiez.androidtraining.features.ex01.domain.User

class Ex01MainActivity : AppCompatActivity() {

    //val viewModels: FormularioViewModel by viewModels()
    val viewModel: Ex01FormularioViewModel by lazy {
        Ex01FormularioViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            ResetUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        setupView()
        setupObservers()
        val editText = findViewById<EditText>(R.id.name)
        editText.visibility = View.INVISIBLE
        val editText2 = findViewById<EditText>(R.id.surname)
        editText2.visibility = View.INVISIBLE
        val myButton = findViewById<Button>(R.id.action_clean)
        myButton.visibility = View.INVISIBLE
        //viewModel.loadUser()
    }


    private fun setupView(){
        //poner uno para recuperar
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(getNameInput(),getSurnameInput())
            viewModel.loadUser()
            val editText = findViewById<EditText>(R.id.name)
            editText.visibility = View.VISIBLE
            val editText2 = findViewById<EditText>(R.id.surname)
            editText2.visibility = View.VISIBLE
            val myButton = findViewById<Button>(R.id.action_clean)
            myButton.visibility = View.VISIBLE
        }

        val actionButton2 = findViewById<Button>(R.id.action_get)
        actionButton2.setOnClickListener {
            findViewById<EditText>(R.id.input_name).setText("")
            findViewById<EditText>(R.id.input_surname).setText("")
        }

        val actionButton3 = findViewById<Button>(R.id.action_clean)
        actionButton3.setOnClickListener {
            viewModel.resetUser()

            val editText = findViewById<EditText>(R.id.name)
            editText.visibility = View.INVISIBLE
            val editText2 = findViewById<EditText>(R.id.surname)
            editText2.visibility = View.INVISIBLE
            val myButton = findViewById<Button>(R.id.action_clean)
            myButton.visibility = View.INVISIBLE

        }
    }

   private fun getNameInput(): String=
       findViewById<EditText>(R.id.input_name).text.toString()
    //primero busca el elemento, luego saca la propiedad con text y luego lo pasa a String

   private fun getSurnameInput(): String=
        findViewById<EditText>(R.id.input_surname).text.toString()



    private fun setupObservers() {
        val observer = Observer<Ex01FormularioViewModel.UiState> {
            //Código al notificar el observador
            it.user?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(user: User) {
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