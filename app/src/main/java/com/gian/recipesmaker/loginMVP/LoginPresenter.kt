package com.gian.recipesmaker.loginMVP

import com.gian.recipesmaker.loginMVP.LoginActivity
import com.gian.recipesmaker.loginMVP.LoginInteractor

class LoginPresenter (var context: LoginActivity?, val loginInteractor: LoginInteractor) :
    LoginInteractor.onLoginFinishedListener {

    fun validateUser(username:String, password:String){
        context?.showProgressDialog()
            loginInteractor.validateEmailAndPassword(username,password,this,context)

    }

    override fun onEmailError() {
        context?.hideProgressDialog()
        context?.showEmailError()

    }

    override fun onPasswordError() {
        context?.hideProgressDialog()
        context?.showPasswordError()
    }

    override fun onSuccess() {
        context?.hideProgressDialog()
        context?.navigateTOActivity()
    }

    override fun onEmailNull() {
        context?.hideProgressDialog()
        context?.showEmailIsNull()
    }

    override fun onPasswordNull() {
        context?.hideProgressDialog()
        context?.showPasswordIsNull()
    }

    override fun onDataBaseError() {
      context?.showDataBaseError()
    }

    fun onDestroy(){
        context = null
    }


}