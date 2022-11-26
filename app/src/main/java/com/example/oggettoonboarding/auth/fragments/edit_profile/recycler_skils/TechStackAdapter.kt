package com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_skils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.RecyclerItemBinding
import com.example.oggettoonboarding.fragments.models.TechStack
import com.example.oggettoonboarding.utils.ItemClickListener

class TechStackAdapter(private val callback: ItemClickListener<TechStack>) :
    ListAdapter<TechStack, TechStacksViewHolder>(TechStackDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TechStacksViewHolder(
        RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: TechStacksViewHolder, position: Int) =
        holder.bind(currentList[position])
}