package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String? = null,
    val sureName: String? = null,
    val patronymic: String? = null,
    val photoUrl: String? = null,
    val job: Job,
    val aboutMe: AboutMe,
): Parcelable
