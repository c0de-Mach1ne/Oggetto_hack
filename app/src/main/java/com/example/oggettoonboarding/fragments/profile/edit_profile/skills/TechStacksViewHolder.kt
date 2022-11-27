package com.example.oggettoonboarding.fragments.profile.edit_profile.skills

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.databinding.RecyclerItemBinding
import com.example.oggettoonboarding.fragments.models.TechStack
import com.example.oggettoonboarding.utils.ItemClickListener

class TechStacksViewHolder(
    private val binding: RecyclerItemBinding,
    private val callback: ItemClickListener<TechStack>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stack: TechStack) {
        binding.ivDeleteItem.setOnClickListener {
            callback.onClickItem(TechStack(stack.stackName))
        }

        binding.tvItemName.text = stack.stackName
    }
}