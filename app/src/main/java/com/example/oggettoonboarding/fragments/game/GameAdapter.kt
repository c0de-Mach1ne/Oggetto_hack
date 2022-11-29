package com.example.oggettoonboarding.fragments.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.ItemEventBinding
import com.example.oggettoonboarding.fragments.models.Game
import com.example.oggettoonboarding.utils.ItemClickListener

class GameAdapter(private val callback: ItemClickListener<Game>) :
    ListAdapter<Game, GameViewHolder>(GameDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameViewHolder(
        ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}