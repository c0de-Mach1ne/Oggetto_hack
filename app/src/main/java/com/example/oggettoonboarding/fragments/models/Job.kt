package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    val jobTitle: String? = null,
    val grade: String? = null,
    val projects: List<Project>? = null,
    val team: String? = null,
    val professionalSkills: List<TechStack>? = null,
): Parcelable
