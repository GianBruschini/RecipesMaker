package com.gian.recipesmaker.SignUpMVP

import android.widget.Toast
import com.gian.recipesmaker.loginMVP.LoginInteractor

class SignUpPresenter(var signUpView: SignUpView,
                      var context:SignUpActivity,
                      var signUpInteractor: SignUpInteractor) :SignUpInteractor.onSignUpListener {

    fun saveRegistrationData(email:String, password:String){
        signUpInteractor.saveRegistrationData(email, password,this)
    }

    override fun onSuccessRegistration() {
        signUpView.successRegistration()
    }

    override fun onEmailExist() {
        signUpView.emailExist()
    }

    override fun onEmailIsNull() {
        signUpView.emailIsNull()
    }

    override fun onPasswordIsNull() {
        signUpView.passwordIsNull()
    }

    override fun onEmailError() {
        signUpView.emailIsNotValid()
    }

    override fun onDatabaseError() {
        signUpView.showDataBaseError()
    }


}