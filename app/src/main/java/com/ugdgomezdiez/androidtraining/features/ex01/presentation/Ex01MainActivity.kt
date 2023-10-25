package com.ugdgomezdiez.androidtraining.features.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
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

        val actionButton2 = findViewById<Button>(R.id.action_clear)
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
        val observer= Observer<Ex01FormularioViewModel.UiState>{
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
        viewModel.uiState.observe(this,observer)}

    private fun showError(error: ErrorApp) {
        Snackbar.make(
            binding.root,
            getString(R.string.label_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading(){
        binding.skeletonLayout.showOriginal()
    }



     fun bindData(users: List<User>) {
         val row1=findViewById<ViewGroup>(R.id.row_1)
         val row2=findViewById<ViewGroup>(R.id.row_2)
         val row3=findViewById<ViewGroup>(R.id.row_3)
         val row4=findViewById<ViewGroup>(R.id.row_4)
         val row5=findViewById<ViewGroup>(R.id.row_5)

         if(users.size>0){
             binding.row1.apply {
                 setNameInput(users[0].username,row1)
                 setSurnameInput(users[0].surname,row1)
                 setIdInput(users[0].id,row1)
                 row1.visibility=View.VISIBLE
                 row1.findViewById<Button>(R.id.action_clean).setOnClickListener {
                     viewModel.deleteUser(users[0].id.toString())
                     deleteText(row1)
                     row1.visibility=View.GONE
                 }
             }
         }

         if(users.size>1){
             binding.row2.apply {
                 setNameInput(users[1].username,row2)
                 setSurnameInput(users[1].surname,row2)
                 setIdInput(users[1].id,row2)
                 row2.visibility=View.VISIBLE
                 row2.findViewById<Button>(R.id.action_clean).setOnClickListener {
                     viewModel.deleteUser(users[1].id.toString())
                     deleteText(row2)
                     row2.visibility=View.GONE
                 }
             }
         }

         if (users.size>2){
             binding.row3.apply {
                 setNameInput(users[2].username,row3)
                 setSurnameInput(users[2].surname,row3)
                 setIdInput(users[2].id,row3)
                 row3.visibility=View.VISIBLE
                 row3.findViewById<Button>(R.id.action_clean).setOnClickListener {
                     viewModel.deleteUser(users[2].id.toString())
                     deleteText(row3)
                     row3.visibility=View.GONE
                 }
             }
         }

         if (users.size>3){
             binding.row4.apply {
                 setNameInput(users[3].username,row4)
                 setSurnameInput(users[3].surname,row4)
                 setIdInput(users[3].id,row4)
                 row4.visibility=View.VISIBLE
                 row4.findViewById<Button>(R.id.action_clean).setOnClickListener {
                     viewModel.deleteUser(users[3].id.toString())
                     deleteText(row4)
                     row4.visibility=View.GONE
                 }
             }
         }

         if (users.size>4){
             binding.row5.apply {
                 setNameInput(users[4].username,row5)
                 setSurnameInput(users[4].surname,row5)
                 setIdInput(users[4].id,row5)
                 row5.visibility=View.VISIBLE
                 row5.findViewById<Button>(R.id.action_clean).setOnClickListener {
                     viewModel.deleteUser(users[4].id.toString())
                     deleteText(row5)
                     row5.visibility=View.GONE
                 }
             }
         }

    }

    private fun deleteText(row:ViewGroup){
        row.findViewById<TextView>(R.id.label_id).text=null
        row.findViewById<TextView>(R.id.name).text=null
        row.findViewById<TextView>(R.id.surname).text=null
    }

    private fun setNameInput(name: String, row:ViewGroup) {
        row.findViewById<TextView>(R.id.name).setText(name)
    }


    private fun setSurnameInput(surname: String,row:ViewGroup) {
        row.findViewById<TextView>(R.id.surname).setText(surname)
    }

    private fun setIdInput(id:Int,row:ViewGroup){
        row.findViewById<TextView>(R.id.label_id).setText(id.toString())
    }

    private fun clearScreen(){
        findViewById<EditText>(R.id.input_surname).setText(null)
        findViewById<EditText>(R.id.input_name).setText(null)
    }


}