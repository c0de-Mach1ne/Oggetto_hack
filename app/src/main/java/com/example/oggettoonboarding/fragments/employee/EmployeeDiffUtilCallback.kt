package com.example.oggettoonboarding.fragments.employee

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.User

class EmployeeDiffUtilCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem == newItem

    override fun areContentsTheSame(oldItem: User, newItem: User) =
        oldItem.name == newItem.name
}