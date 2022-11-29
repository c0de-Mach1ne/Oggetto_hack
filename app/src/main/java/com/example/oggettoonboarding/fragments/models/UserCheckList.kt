package com.example.oggettoonboarding.fragments.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserCheckList(
    val uid: String? = null,
    val listCheckList: List<CheckList>? = null,
): Parcelable
