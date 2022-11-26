package com.example.oggettoonboarding.auth.fragments.edit_profile.project

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.Project

class ProjectDiffUtilCallback : DiffUtil.ItemCallback<Project>() {

    override fun areItemsTheSame(oldItem: Project, newItem: Project) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Project, newItem: Project) =
        oldItem.projectName == newItem.projectName
}