package com.example.oggettoonboarding.repositories

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UserRepository {

    private val db = Firebase.storage

    fun getInstance() = db.reference.child("images/")
}