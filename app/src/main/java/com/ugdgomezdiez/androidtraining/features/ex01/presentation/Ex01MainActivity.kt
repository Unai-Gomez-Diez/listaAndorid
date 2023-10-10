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
        val editText3 = findViewById<EditText>(R.id.name2)
        editText3.visibility = View.INVISIBLE
        val editText4 = findViewById<EditText>(R.id.surname2)
        editText4.visibility = View.INVISIBLE
        val myButton2 = findViewById<Button>(R.id.action_clean2)
        myButton2.visibility = View.INVISIBLE
        val editText5 = findViewById<EditText>(R.id.name3)
        editText5.visibility = View.INVISIBLE
        val editText6 = findViewById<EditText>(R.id.surname3)
        editText6.visibility = View.INVISIBLE
        val myButton3 = findViewById<Button>(R.id.action_clean3)
        myButton3.visibility = View.INVISIBLE
        val editText7 = findViewById<EditText>(R.id.name4)
        editText7.visibility = View.INVISIBLE
        val editText8 = findViewById<EditText>(R.id.surname4)
        editText8.visibility = View.INVISIBLE
        val myButton4 = findViewById<Button>(R.id.action_clean4)
        myButton4.visibility = View.INVISIBLE
        val editText9 = findViewById<EditText>(R.id.name5)
        editText9.visibility = View.INVISIBLE
        val editText10 = findViewById<EditText>(R.id.surname5)
        editText10.visibility = View.INVISIBLE
        val myButton5 = findViewById<Button>(R.id.action_clean5)
        myButton5.visibility = View.INVISIBLE
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
            val editText3 = findViewById<EditText>(R.id.name2)
            editText3.visibility = View.VISIBLE
            val editText4 = findViewById<EditText>(R.id.surname2)
            editText4.visibility = View.VISIBLE
            val myButton2 = findViewById<Button>(R.id.action_clean2)
            myButton2.visibility = View.VISIBLE
            val editText5 = findViewById<EditText>(R.id.name3)
            editText5.visibility = View.VISIBLE
            val editText6 = findViewById<EditText>(R.id.surname3)
            editText6.visibility = View.VISIBLE
            val myButton3 = findViewById<Button>(R.id.action_clean3)
            myButton3.visibility = View.VISIBLE
            val editText7 = findViewById<EditText>(R.id.name4)
            editText7.visibility = View.VISIBLE
            val editText8 = findViewById<EditText>(R.id.surname4)
            editText8.visibility = View.VISIBLE
            val myButton4 = findViewById<Button>(R.id.action_clean4)
            myButton4.visibility = View.VISIBLE
            val editText9 = findViewById<EditText>(R.id.name5)
            editText9.visibility = View.VISIBLE
            val editText10 = findViewById<EditText>(R.id.surname5)
            editText10.visibility = View.VISIBLE
            val myButton5 = findViewById<Button>(R.id.action_clean5)
            myButton5.visibility = View.VISIBLE
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
        val actionButton4 = findViewById<Button>(R.id.action_clean2)
        actionButton4.setOnClickListener {
            viewModel.resetUser()

            val editText = findViewById<EditText>(R.id.name2)
            editText.visibility = View.INVISIBLE
            val editText2 = findViewById<EditText>(R.id.surname2)
            editText2.visibility = View.INVISIBLE
            val myButton = findViewById<Button>(R.id.action_clean2)
            myButton.visibility = View.INVISIBLE

        }
        val actionButton5 = findViewById<Button>(R.id.action_clean3)
        actionButton5.setOnClickListener {
            viewModel.resetUser()

            val editText = findViewById<EditText>(R.id.name3)
            editText.visibility = View.INVISIBLE
            val editText2 = findViewById<EditText>(R.id.surname3)
            editText2.visibility = View.INVISIBLE
            val myButton = findViewById<Button>(R.id.action_clean3)
            myButton.visibility = View.INVISIBLE

        }
        val actionButton6 = findViewById<Button>(R.id.action_clean4)
        actionButton6.setOnClickListener {
            viewModel.resetUser()

            val editText = findViewById<EditText>(R.id.name4)
            editText.visibility = View.INVISIBLE
            val editText2 = findViewById<EditText>(R.id.surname4)
            editText2.visibility = View.INVISIBLE
            val myButton = findViewById<Button>(R.id.action_clean4)
            myButton.visibility = View.INVISIBLE

        }
        val actionButton7 = findViewById<Button>(R.id.action_clean5)
        actionButton7.setOnClickListener {
            viewModel.resetUser()

            val editText = findViewById<EditText>(R.id.name5)
            editText.visibility = View.INVISIBLE
            val editText2 = findViewById<EditText>(R.id.surname5)
            editText2.visibility = View.INVISIBLE
            val myButton = findViewById<Button>(R.id.action_clean5)
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