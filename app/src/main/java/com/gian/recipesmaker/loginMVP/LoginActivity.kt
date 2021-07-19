package com.gian.recipesmaker.loginMVP

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.gian.recipesmaker.Home.HomeActivity
import com.gian.recipesmaker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {
    private val loginPresenter = LoginPresenter(this, LoginInteractor())
    private lateinit var binding: ActivityLoginBinding
    private lateinit var progressDialog:ProgressDialog
    private lateinit var awesomeValidation: AwesomeValidation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
    }

    private fun initializeUI() {
        binding.buttonLogin.setOnClickListener {
            loginPresenter.validateUser(binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString())
        }

        binding.buttonSignUp.setOnClickListener {

        }
    }

    override fun showProgressDialog() {
        progressDialog = ProgressDialog(this@LoginActivity)
        progressDialog.setTitle("Cargando")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    override fun showEmailError() {
        Toast.makeText(this,"Error al ingresar el email",Toast.LENGTH_LONG).show()
    }

    override fun showPasswordError() {
        Toast.makeText(this,"Error al ingresar la password",Toast.LENGTH_LONG).show()
    }

    override fun showEmailIsNull() {
        Toast.makeText(this,"El email es null",Toast.LENGTH_LONG).show()
    }

    override fun showPasswordIsNull() {
        Toast.makeText(this,"La password es null",Toast.LENGTH_LONG).show()
    }


    override fun showDataBaseError() {
        Toast.makeText(this,"La password es null",Toast.LENGTH_LONG).show()
    }

    override fun navigateTOActivity() {
        navigateToActivity(Intent(this, HomeActivity::class.java))
    }

    private fun navigateToActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.onDestroy()
    }
}