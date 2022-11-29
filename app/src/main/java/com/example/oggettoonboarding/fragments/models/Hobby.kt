package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hobby(
    val hobbyName: String? = null
): Parcelable
