package com.gian.recipesmaker.loginmvp

interface LoginView {
    fun showProgressDialog()
    fun hideProgressDialog()
    fun showEmailError()
    fun showPasswordError()
    fun showEmailIsNull()
    fun showPasswordIsNull()
    fun showDataBaseError()
    fun navigateTOActivity()
}