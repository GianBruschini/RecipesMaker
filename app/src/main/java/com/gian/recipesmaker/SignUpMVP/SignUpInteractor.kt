package com.gian.recipesmaker.SignUpMVP

import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignUpInteractor {

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    interface onSignUpListener {
        fun onSuccessRegistration()
        fun onEmailExist()
        fun onEmailIsNull()
        fun onPasswordIsNull()
        fun onEmailError()
        fun onDatabaseError()
    }

    fun saveRegistrationData(email:String, password:String, listener: onSignUpListener){
        when{
            email.isEmpty() -> listener.onEmailIsNull()
            password.isEmpty() -> listener.onPasswordIsNull()
            !EMAIL_ADDRESS_PATTERN.matcher(email).matches() -> listener.onEmailError()
            else -> checkIfUserEmailExistAndStore(email,listener)
        }

    }

    private fun checkIfUserEmailExistAndStore(email: String, listener: onSignUpListener) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val userNameRef = rootRef.child("Users").
        child("Accounts").child(email)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onEmailExist()
                }else{
                    storeInFirebaseDatabase(email,listener)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        userNameRef.addListenerForSingleValueEvent(eventListener)
    }

    private fun storeInFirebaseDatabase(
        email: String,
        listener: onSignUpListener
    ) {
        val databaseAccount = Firebase.database.
            getReference("Users")
            .child("accounts")

        databaseAccount.setValue(email)
        listener.onSuccessRegistration()
    }
}