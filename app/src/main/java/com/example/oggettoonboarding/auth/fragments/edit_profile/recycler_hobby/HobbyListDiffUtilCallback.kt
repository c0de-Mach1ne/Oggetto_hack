package com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_hobby

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.Hobby

class HobbyListDiffUtilCallback : DiffUtil.ItemCallback<Hobby>() {

    override fun areItemsTheSame(oldItem: Hobby, newItem: Hobby) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Hobby, newItem: Hobby) =
        oldItem.hobbyName == newItem.hobbyName
}