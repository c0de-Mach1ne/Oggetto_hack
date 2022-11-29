package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project (
    val projectName: String? = null
): Parcelable