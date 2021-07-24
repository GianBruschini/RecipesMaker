package com.gian.recipesmaker.loginmvp

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.regex.Pattern


class LoginInteractor {
    private lateinit var mFirebaseAuth:FirebaseAuth
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
            email.isEmpty() ->  listener.onEmailNull()
            password.isEmpty() -> listener.onPasswordNull()
            !EMAIL_ADDRESS_PATTERN.matcher(email).matches() -> listener.onEmailError()
            else -> {
                if (context != null) {
                    checkIfUserExistAndSignIn(context,email,password,listener)

                }
            }
        }
    }

    private fun checkIfUserExistAndSignIn(
        context: LoginActivity,
        email: String,
        password: String,
        listener: onLoginFinishedListener
    ) {
        val email = encodeString(email)
        val rootRef = FirebaseDatabase.getInstance().reference
        val userNameRef = rootRef.child("Users").
        child("accounts").orderByChild("email").equalTo(email)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    checkIfPasswordIsCorrect(password,listener)
                }else{
                    listener.onEmailError()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context,"Database error",Toast.LENGTH_SHORT).show() //Don't ignore errors!
            }
        }
        userNameRef.addListenerForSingleValueEvent(eventListener)
    }

    private fun checkIfPasswordIsCorrect(password: String, listener: onLoginFinishedListener) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val passwordRef = rootRef.child("Users").
        child("accounts").orderByChild("password").equalTo(password)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onSuccess()
                }else{
                    listener.onPasswordError()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                listener.onDataBaseError()
            }
        }

        passwordRef.addListenerForSingleValueEvent(eventListener)
    }

    fun encodeString(string: String): String {
        return string.replace(".", ",")
    }





}