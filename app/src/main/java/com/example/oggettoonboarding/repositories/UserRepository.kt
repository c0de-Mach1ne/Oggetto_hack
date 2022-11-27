package com.example.oggettoonboarding.repositories

import com.example.oggettoonboarding.fragments.models.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UserRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val storage = Firebase.storage
    private val db = Firebase.database.reference

    fun getCurrentUser() = firebaseAuth.currentUser
    fun getInstance() = storage.reference.child("images/")
    fun getUsers() = db.child("Users").get()

    fun pushStandardUserCheckList(listCheckList: List<CheckList>) =
        db.child("StandardCheckList").setValue(listCheckList)

    fun getStandardCheckList() = db.child("StandardCheckList").get()

    fun initUserCheckList(userCheckList: UserCheckList) =
        userCheckList.uid?.let { db.child("UserCheckList").child(it) }?.setValue(
            UserCheckList(
                uid = userCheckList.uid,
                listCheckList = userCheckList.listCheckList,
            )
        )

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

    fun pushEvent(event: Event, key: String) =
        db.child("Events").child(key).setValue(event)


    fun getEvent() = db.child("Events").get()
}