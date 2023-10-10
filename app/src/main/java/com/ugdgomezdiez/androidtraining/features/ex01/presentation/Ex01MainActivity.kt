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
        val viewGroup1 = findViewById<ViewGroup>(R.id.row_1)
        viewGroup1.visibility=View.GONE
        val viewGroup2 = findViewById<ViewGroup>(R.id.row_2)
        viewGroup2.visibility=View.GONE
        val viewGroup3 = findViewById<ViewGroup>(R.id.row_3)
        viewGroup3.visibility=View.GONE
        val viewGroup4 = findViewById<ViewGroup>(R.id.row_4)
        viewGroup4.visibility=View.GONE
        val viewGroup5 = findViewById<ViewGroup>(R.id.row_5)
        viewGroup5.visibility=View.GONE

        //viewModel.loadUser()
    }


    private fun setupView(){
        //poner uno para recuperar
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(getNameInput(),getSurnameInput())
            viewModel.loadUser()

            findViewById<ViewGroup>(R.id.row_1).findViewById<TextView>(R.id.name)
        }

        val actionButton2 = findViewById<Button>(R.id.action_get)
        actionButton2.setOnClickListener {
            findViewById<EditText>(R.id.input_name).setText("")
            findViewById<EditText>(R.id.input_surname).setText("")
        }

        val actionButton3 = findViewById<ViewGroup>(R.id.row_1).findViewById<Button>(R.id.action_clean)
        actionButton3.setOnClickListener {
            viewModel.resetUser()

            val viewGroup = findViewById<ViewGroup>(R.id.row_1)
            viewGroup.visibility=View.GONE


        }
        val actionButton4 = findViewById<ViewGroup>(R.id.row_2).findViewById<Button>(R.id.action_clean)
        actionButton4.setOnClickListener {
            viewModel.resetUser()

            val viewGroup = findViewById<ViewGroup>(R.id.row_2)
            viewGroup.visibility=View.GONE


        }
        val actionButton5 = findViewById<ViewGroup>(R.id.row_3).findViewById<Button>(R.id.action_clean)
        actionButton5.setOnClickListener {
            viewModel.resetUser()

            val viewGroup = findViewById<ViewGroup>(R.id.row_3)
            viewGroup.visibility=View.GONE


        }
        val actionButton6 = findViewById<ViewGroup>(R.id.row_4).findViewById<Button>(R.id.action_clean)
        actionButton6.setOnClickListener {
            viewModel.resetUser()

            val viewGroup = findViewById<ViewGroup>(R.id.row_4)
            viewGroup.visibility=View.GONE


        }
        val actionButton7 = findViewById<ViewGroup>(R.id.row_5).findViewById<Button>(R.id.action_clean)
        actionButton7.setOnClickListener {
            viewModel.resetUser()

            val viewGroup = findViewById<ViewGroup>(R.id.row_5)
            viewGroup.visibility=View.GONE


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