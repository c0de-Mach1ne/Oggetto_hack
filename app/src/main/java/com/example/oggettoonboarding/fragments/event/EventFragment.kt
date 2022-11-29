package com.example.oggettoonboarding.fragments.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.navOptions
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEventBinding
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.fragments.tabs.TabsFragmentDirections
import com.example.oggettoonboarding.utils.ItemClickListener
import com.example.oggettoonboarding.utils.findTopNavController

class EventFragment : Fragment(R.layout.fragment_event) {

    private val viewModel by viewModels<EventViewModel> { EventViewModelFactory() }
    private lateinit var binding: FragmentEventBinding
    private val eventAdapter by lazy {
        EventAdapter(object : ItemClickListener<Event> {
            override fun onClickItem(value: Event) {
                findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToEventDescriptionFragment(
                    value), navOptions {
                    popUpTo(R.id.profileFragment) {
                        inclusive = true
                    }
                })
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        initRecycler()
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEvents()
        binding.fabAddEvent.setOnClickListener {
            findTopNavController().navigate(R.id.action_tabsFragment_to_createEventFragment,
                null,
                navOptions {
                    popUpTo(R.id.eventFragment) {
                        inclusive = true
                    }
                })
        }
    }

    private fun observeViewModel() {
        viewModel.eventList.observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
            eventAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecycler() = with(binding) {
        recycler.adapter = eventAdapter
    }
}