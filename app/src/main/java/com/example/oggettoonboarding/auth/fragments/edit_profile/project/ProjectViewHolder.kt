package com.example.oggettoonboarding.auth.fragments.edit_profile.project

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.databinding.RecyclerItemBinding
import com.example.oggettoonboarding.fragments.models.Project
import com.example.oggettoonboarding.utils.ItemClickListener

class ProjectViewHolder(
    private val binding: RecyclerItemBinding,
    private val callback: ItemClickListener<Project>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(projectName: Project) {
        binding.ivDeleteItem.setOnClickListener {
            callback.onClickItem(Project(projectName.projectName))
        }

        binding.tvItemName.text = projectName.projectName
    }
}