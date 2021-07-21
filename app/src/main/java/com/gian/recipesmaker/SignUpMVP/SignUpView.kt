package com.gian.recipesmaker.SignUpMVP

interface SignUpView {
    fun successRegistration()
    fun emailIsNull()
    fun passwordIsNull()
    fun emailNotExits()
    fun showDataBaseError()

}