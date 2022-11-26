package com.example.oggettoonboarding.fragments.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String? = null,
    val sureName: String? = null,
    val patronymic: String? = null,
    val photoUrl: String? = null,
    val job: Job? = null,
    val aboutMe: AboutMe? = null,
): Parcelable
