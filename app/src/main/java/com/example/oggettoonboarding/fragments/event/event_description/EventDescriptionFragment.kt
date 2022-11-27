package com.example.oggettoonboarding.fragments.event.event_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEventDescriptionBinding
import com.example.oggettoonboarding.fragments.event.EventViewModel
import com.example.oggettoonboarding.fragments.event.EventViewModelFactory

class EventDescriptionFragment: Fragment(R.layout.fragment_event_description) {

    private lateinit var binding: FragmentEventDescriptionBinding
    private val viewModel by viewModels<EventViewModel> { EventViewModelFactory() }
    private val navArg by navArgs<EventDescriptionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEventDescriptionBinding.inflate(layoutInflater, container, false)
        binding.tvTest.text = navArg.event.title
        return binding.root
    }

}