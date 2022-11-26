package com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_hobby

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.databinding.RecyclerItemBinding
import com.example.oggettoonboarding.fragments.models.Hobby
import com.example.oggettoonboarding.utils.ItemClickListener

class HobbyListViewHolder(
    private val binding: RecyclerItemBinding,
    private val callback: ItemClickListener<Hobby>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hobby: Hobby) {
        binding.ivDeleteItem.setOnClickListener { callback.onClickItem(Hobby(hobby.hobbyName)) }
        binding.tvItemName.text = hobby.hobbyName
    }
}