package com.gian.recipesmaker.SignUpMVP

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
        TODO("Not yet implemented")
    }

    override fun onEmailIsNull() {
        signUpView.emailIsNull()
    }

    override fun onPasswordIsNull() {
        signUpView.passwordIsNull()
    }

    override fun onEmailError() {
        signUpView.emailNotExits()
    }

    override fun onDatabaseError() {
        signUpView.showDataBaseError()
    }


}