package com.gian.recipesmaker.loginMVP

import android.content.Intent
import com.gian.recipesmaker.Home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginInteractor {
    private lateinit var mFirebaseAuth:FirebaseAuth ;
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    interface onLoginFinishedListener{
        fun onEmailError()
        fun onPasswordError()
        fun onSuccess()
        fun onEmailNull()
        fun onPasswordNull()
        fun onDataBaseError()
    }

    fun validateEmailAndPassword(
        email: String, password: String,
        listener: onLoginFinishedListener,
        context: LoginActivity?
    ) {
        mFirebaseAuth = FirebaseAuth.getInstance()

        when {
            email.isEmpty() -> listener.onEmailNull()
            password.isEmpty() -> listener.onPasswordNull()
            !EMAIL_ADDRESS_PATTERN.matcher(email).matches() -> listener.onEmailError()
        }

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (context != null) {
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(context) { task ->
                        if (task.isSuccessful) {
                            listener.onSuccess()
                            navigateToActivity(context, Intent(context, HomeActivity::class.java))
                        } else {
                            listener.onDataBaseError()
                        }
                    }
            }
        }
    }

    private fun navigateToActivity(context: LoginActivity, intent: Intent) {
        context.startActivity(intent)
        context.finish()
    }

}