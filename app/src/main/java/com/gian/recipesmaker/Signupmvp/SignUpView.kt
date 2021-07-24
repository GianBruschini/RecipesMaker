package com.gian.recipesmaker.Signupmvp

interface SignUpView {
    fun successRegistration()
    fun emailIsNull()
    fun passwordIsNull()
    fun emailExist()
    fun showDataBaseError()
    fun emailIsNotValid()


}