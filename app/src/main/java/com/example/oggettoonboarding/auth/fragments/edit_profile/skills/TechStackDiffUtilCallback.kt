package com.example.oggettoonboarding.auth.fragments.edit_profile.skills

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.TechStack

class TechStackDiffUtilCallback : DiffUtil.ItemCallback<TechStack>() {

    override fun areItemsTheSame(oldItem: TechStack, newItem: TechStack) = oldItem == newItem

    override fun areContentsTheSame(oldItem: TechStack, newItem: TechStack) =
        oldItem.stackName == newItem.stackName
}