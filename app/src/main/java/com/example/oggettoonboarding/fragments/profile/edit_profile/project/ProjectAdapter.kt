package com.example.oggettoonboarding.fragments.profile.edit_profile.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.RecyclerItemBinding
import com.example.oggettoonboarding.fragments.models.Project
import com.example.oggettoonboarding.utils.ItemClickListener

class ProjectAdapter(private val callback: ItemClickListener<Project>) :
    ListAdapter<Project, ProjectViewHolder>(ProjectDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProjectViewHolder(
        RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) =
        holder.bind(currentList[position])
}