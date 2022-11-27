package com.example.oggettoonboarding.fragments.event

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.fragments.models.User

class EventDiffUtilCallback: DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Event, newItem: Event) =
        oldItem.description == newItem.description
}