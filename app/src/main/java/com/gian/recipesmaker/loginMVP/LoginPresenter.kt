package com.gian.recipesmaker.loginMVP

import android.content.Context
import com.gian.recipesmaker.loginMVP.LoginActivity
import com.gian.recipesmaker.loginMVP.LoginInteractor




class LoginPresenter
constructor(var loginView: LoginView?,
            val loginInteractor: LoginInteractor,
            val context: LoginActivity): LoginInteractor.onLoginFinishedListener  {

    fun validateUser(username:String, password:String){
        loginView!!.showProgressDialog()
        loginInteractor.validateEmailAndPassword(username,password,this,context)

    }

    override fun onEmailError() {
        loginView!!.hideProgressDialog()
        loginView!!.showEmailError()

    }

    override fun onPasswordError() {
        loginView!!.hideProgressDialog()
        loginView!!.showPasswordError()
    }

    override fun onSuccess() {
        loginView!!.hideProgressDialog()
        loginView!!.navigateTOActivity()
    }

    override fun onEmailNull() {
        loginView!!.showEmailIsNull()
        loginView!!.hideProgressDialog()
    }

    override fun onPasswordNull() {
        loginView!!.hideProgressDialog()
        loginView!!.showPasswordIsNull()
    }

    override fun onDataBaseError() {
        loginView!!.showDataBaseError()
    }

    fun onDestroy(){
        loginView = null
    }


}