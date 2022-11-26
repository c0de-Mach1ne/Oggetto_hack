package com.example.oggettoonboarding.repository

import com.example.oggettoonboarding.auth.models.UserAuth
import com.example.oggettoonboarding.auth.models.UserPersonalInfo
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.core.Single

class AuthRepository() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.database.reference

    fun signIn(userAuth: UserAuth) =
        firebaseAuth.signInWithEmailAndPassword(userAuth.email, userAuth.password)

    fun signUpWithEmailAndPass(userAuth: UserAuth) =
        firebaseAuth.createUserWithEmailAndPassword(userAuth.email, userAuth.password)

    fun signUpWithPersonalInfo(userPersonalInfo: UserPersonalInfo) =
        firebaseAuth.currentUser?.let { firebaseUser ->
            db.child("Users").child(firebaseUser.uid).setValue(
                UserPersonalInfo(
                    email = userPersonalInfo.email,
                    name = userPersonalInfo.name,
                    sureName = userPersonalInfo.sureName,
                    patronymic = userPersonalInfo.patronymic,
                )
            )
        }

    fun getDatabaseUser() =
        firebaseAuth.uid?.let { uid ->
            db.child("Users").child(uid).get()
        }

    fun getAuthUser() = firebaseAuth.currentUser

    fun signOut() =
        firebaseAuth.signOut()
}