package com.example.oggettoonboarding.fragments.game

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.databinding.ItemEventBinding
import com.example.oggettoonboarding.fragments.models.Game
import com.example.oggettoonboarding.utils.ItemClickListener

class GameViewHolder(
    private val binding: ItemEventBinding,
    private val callback: ItemClickListener<Game>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game) {
        binding.tvEventTitle.text = game.title

        binding.root.setOnClickListener {

        }
    }
}