package com.example.oggettoonboarding.fragments.models

data class User(
    val name: String? = null,
    val sureName: String? = null,
    val patronymic: String? = null,
    val photoUrl: String? = null,
    val job: Job,
    val aboutMe: AboutMe,
)
