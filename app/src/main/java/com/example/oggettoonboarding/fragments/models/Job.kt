package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    val jobTitle: String? = null,
    val grade: List<String>? = null,
    val projects: List<String>? = null,
    val team: String? = null,
): Parcelable
