package com.example.oggettoonboarding.auth.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPersonalInfo (
    val email: String? = null,
    val name: String? = null,
    val sureName: String? = null,
    val patronymic: String? = null,
): Parcelable