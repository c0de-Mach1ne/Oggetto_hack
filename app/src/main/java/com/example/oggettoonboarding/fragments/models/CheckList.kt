package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckList(
    val checkTitle: String? = null,
    val checkDescription: String? = null,
    val flagCheck: Boolean? = null,
): Parcelable
