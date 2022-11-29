package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AboutMe(
    val city: String? = null,
    val hobby: List<String>? = null,
): Parcelable