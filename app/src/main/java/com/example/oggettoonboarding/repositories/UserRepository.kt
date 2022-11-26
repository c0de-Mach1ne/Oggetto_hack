package com.example.oggettoonboarding.repositories

import com.example.oggettoonboarding.fragments.models.AboutMe
import com.example.oggettoonboarding.fragments.models.Job
import com.example.oggettoonboarding.fragments.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UserRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val storage = Firebase.storage
    private val db = Firebase.database.reference


    fun getInstance() = storage.reference.child("images/")

    fun pushUserToDb(user: User) =
        firebaseAuth.currentUser?.let { firebaseUser ->
            db.child("Users").child(firebaseUser.uid).setValue(
                User(
                    name = user.name ?: "",
                    sureName = user.sureName ?: "",
                    patronymic = user.patronymic ?: "",
                    photoUrl = user.photoUrl,
                    Job(
                        jobTitle = user.job?.jobTitle,
                        grade = user.job?.grade,
                        projects = user.job?.projects,
                        team = user.job?.team,
                        professionalSkills = user.job?.professionalSkills,
                    ),
                    AboutMe(
                        city = user.aboutMe?.city,
                        hobby = user.aboutMe?.hobby,
                    )
                )
            )
        }
}