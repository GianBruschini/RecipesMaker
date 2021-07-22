package com.gian.recipesmaker.SignUpMVP

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap

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

    fun saveRegistrationData(email: String, password: String, listener: onSignUpListener){
        when{
            email.isEmpty() -> listener.onEmailIsNull()
            password.isEmpty() -> listener.onPasswordIsNull()
            !EMAIL_ADDRESS_PATTERN.matcher(email).matches() -> listener.onEmailError()
            else -> checkIfUserEmailExistAndStore(email, password, listener)
        }

    }

    private fun checkIfUserEmailExistAndStore(
        email: String,
        password: String,
        listener: onSignUpListener
    ) {
        val email = encodeString(email)
        val rootRef = FirebaseDatabase.getInstance().reference
        val userNameRef = rootRef.child("Users").
        child("accounts").orderByChild("email").equalTo(email)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onEmailExist()
                }else{
                    storeInFirebaseDatabase(email, password, listener)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        userNameRef.addListenerForSingleValueEvent(eventListener)
    }

    private fun storeInFirebaseDatabase(
        email: String,
        password: String,
        listener: onSignUpListener
    ) {

        val crearUser: HashMap<String, Any> = HashMap()
        val email = encodeString(email)
        crearUser["email"] = email
        crearUser["password"] = password
        val key = FirebaseDatabase.getInstance().getReference("Accounts").push().key
        Firebase.database.
            getReference("Users")
            .child("accounts")
            .child(key.toString())
            .updateChildren(crearUser)
        listener.onSuccessRegistration()
    }

    fun encodeString(string: String): String {
        return string.replace(".", ",")
    }
}