package com.gian.recipesmaker.Signupmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gian.recipesmaker.R
import com.gian.recipesmaker.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(),SignUpView,View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpPresenter = SignUpPresenter(this,this,SignUpInteractor())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signUp.setOnClickListener(this)
    }

    override fun successRegistration() {
            Toast.makeText(this,"Successful registration",Toast.LENGTH_SHORT).show()
            finish()
    }

    override fun emailIsNull() {
        binding.emailSignUp.error = getString(R.string.errorEmailNull)
    }

    override fun passwordIsNull() {
        binding.passwordlSignUp.error = getString(R.string.errorPasswordNull)
    }

    override fun emailExist() {
        Toast.makeText(this,"Email repeated",Toast.LENGTH_SHORT).show()
    }


    override fun showDataBaseError() {
        Toast.makeText(this,"Database Error, try again later",Toast.LENGTH_SHORT).show()
    }

    override fun emailIsNotValid() {
        Toast.makeText(this,"Email is not valid",Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.signUp -> {
                signUpPresenter.saveRegistrationData(
                    binding.emailSignUp.text.toString(),
                    binding.passwordlSignUp.text.toString())
            }
        }
    }

}