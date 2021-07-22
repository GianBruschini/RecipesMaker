package com.gian.recipesmaker.loginMVP

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.gian.recipesmaker.Home.HomeActivity
import com.gian.recipesmaker.SignUpMVP.SignUpActivity
import com.gian.recipesmaker.databinding.ActivityLoginBinding




class LoginActivity : AppCompatActivity(), LoginView {

    private val loginPresenter= LoginPresenter(this,LoginInteractor(),this)
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
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        binding.buttonLogin.setOnClickListener {
            loginPresenter.validateUser(binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString())
        }

        binding.buttonSignUp.setOnClickListener {
            navigateToActivity(Intent(this,SignUpActivity::class.java))
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
        Toast.makeText(this,"Email not exist",Toast.LENGTH_LONG).show()
    }

    override fun showPasswordError() {
        Toast.makeText(this,"Password not exist",Toast.LENGTH_LONG).show()
    }

    override fun showEmailIsNull() {
        binding.emailEditText.error = "You have to type an email"
    }

    override fun showPasswordIsNull() {
        binding.passwordEditText.error = "You have to type a password"
    }

    override fun showDataBaseError() {

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