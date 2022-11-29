package com.example.oggettoonboarding.fragments.profile.edit_profile.hobby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.RecyclerItemBinding
import com.example.oggettoonboarding.fragments.models.Hobby
import com.example.oggettoonboarding.utils.ItemClickListener

class HobbyListAdapter(private val callback: ItemClickListener<Hobby>) :
    ListAdapter<Hobby, HobbyListViewHolder>(HobbyListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HobbyListViewHolder(
        RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: HobbyListViewHolder, position: Int) =
        holder.bind(currentList[position])
}