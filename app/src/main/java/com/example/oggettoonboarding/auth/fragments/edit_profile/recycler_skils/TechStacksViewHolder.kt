package com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_skils

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.databinding.TechStackItemBinding
import com.example.oggettoonboarding.fragments.models.TechStack
import com.example.oggettoonboarding.utils.ItemClickListener

class TechStacksViewHolder(
    private val binding: TechStackItemBinding,
    private val callback: ItemClickListener<TechStack>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stack: TechStack) {
        binding.ivDeleteTag.setOnClickListener {
            callback.onClickItem(TechStack(stack.stackName))

        }

        binding.tvTechStack.text = stack.stackName
    }
}