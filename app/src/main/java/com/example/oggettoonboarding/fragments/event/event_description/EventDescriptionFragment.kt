package com.example.oggettoonboarding.fragments.event.event_description

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEventDescriptionBinding
import com.example.oggettoonboarding.fragments.event.EventViewModel
import com.example.oggettoonboarding.fragments.event.EventViewModelFactory

class EventDescriptionFragment: Fragment(R.layout.fragment_event_description) {

    private lateinit var binding: FragmentEventDescriptionBinding
    private val viewModel by viewModels<EventViewModel> { EventViewModelFactory() }


}