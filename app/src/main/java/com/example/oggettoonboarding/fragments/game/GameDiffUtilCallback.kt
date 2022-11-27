package com.example.oggettoonboarding.fragments.game

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.Game

class GameDiffUtilCallback : DiffUtil.ItemCallback<Game>() {

    override fun areItemsTheSame(oldItem: Game, newItem: Game) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Game, newItem: Game) =
        oldItem.title == newItem.title
}