package com.gian.recipesmaker.SignUpMVP

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import com.gian.recipesmaker.R
import com.gian.recipesmaker.databinding.ActivityLoginBinding
import com.gian.recipesmaker.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(),SignUpView {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpPresenter = SignUpPresenter(this,SignUpInteractor())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun successRegistration() {

    }
}