package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val title: String? = null,
    val description: String? = null,
    val date: String? = null,
    val time: String? = null,
    val address: String? = null,
    val photoUrl: String? = null,
    val owner: String? = null,
    val members: List<String>? = null,
) : Parcelable