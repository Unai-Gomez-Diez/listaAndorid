package com.ugdgomezdiez.androidtraining.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.data.UserDataRepository
import com.ugdgomezdiez.androidtraining.data.local.XmlLocalDataSource
import com.ugdgomezdiez.androidtraining.domain.SaveUserUseCase

class MainActivity : AppCompatActivity() {

    //val viewModels: FormularioViewModel by viewModels()
    val viewModel: FormularioViewModel by lazy {
        FormularioViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        setupView()
    }


    private fun setupView(){
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(getNameInput(),getSurnameInput(),getDateInput())
        }
    }

   private fun getNameInput(): String=
       findViewById<EditText>(R.id.input_name).text.toString()
    //primero busca el elemento, luego saca la propiedad con text y luego lo pasa a String

   private fun getSurnameInput(): String=
        findViewById<EditText>(R.id.input_surname).text.toString()

   private fun getDateInput(): String=
       findViewById<EditText>(R.id.input_date_born).text.toString()

}