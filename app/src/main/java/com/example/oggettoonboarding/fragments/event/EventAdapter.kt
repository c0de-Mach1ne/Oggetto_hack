package com.example.oggettoonboarding.fragments.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.ItemEventBinding
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.utils.ItemClickListener

class EventAdapter(private val callback: ItemClickListener<Event>) :
    ListAdapter<Event, EventViewHolder>(EventDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventViewHolder(
        ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}