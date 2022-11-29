package com.example.oggettoonboarding.fragments.event

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oggettoonboarding.databinding.ItemEventBinding
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.utils.ItemClickListener

class EventViewHolder(
    private val binding: ItemEventBinding,
    private val callback: ItemClickListener<Event>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: Event) {
        Glide.with(binding.root).load(event.photoUrl).into(binding.ivEventPoster)
        binding.tvEventTitle.text = event.title
        binding.root.setOnClickListener {
            callback.onClickItem(event)
        }
    }
}