package com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_hobby

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.databinding.HobbyItemBinding
import com.example.oggettoonboarding.fragments.models.Hobby
import com.example.oggettoonboarding.utils.ItemClickListener

class HobbyListViewHolder(
    private val binding: HobbyItemBinding,
    private val callback: ItemClickListener<String>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hobby: Hobby) {
        binding.ivDeleteTag.setOnClickListener { callback.onClickItem(hobby.hobbyName) }
        binding.tvHobbyTag.text = hobby.hobbyName
    }
}