package com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_hobby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.HobbyItemBinding
import com.example.oggettoonboarding.fragments.models.Hobby
import com.example.oggettoonboarding.utils.ItemClickListener

class HobbyListAdapter(private val callback: ItemClickListener<String>) :
    ListAdapter<Hobby, HobbyListViewHolder>(HobbyListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HobbyListViewHolder(
        HobbyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: HobbyListViewHolder, position: Int) =
        holder.bind(currentList[position])
}