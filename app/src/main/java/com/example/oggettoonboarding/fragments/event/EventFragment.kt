package com.example.oggettoonboarding.fragments.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEventBinding
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.utils.ItemClickListener

class EventFragment : Fragment(R.layout.fragment_event) {

    private val viewModel by viewModels<EventViewModel> { EventViewModelFactory() }
    private lateinit var binding: FragmentEventBinding
    private val eventAdapter by lazy {
        EventAdapter(object : ItemClickListener<Event> {
            override fun onClickItem(value: Event) {
                // TODO: тут мы переходим на след экран с сейф аргами
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
        viewModel.getEvents()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddEvent.setOnClickListener {

            // TODO: здесь прокидывается переход на след экран, а уже там
            //  мы работаем с заполнением полей и созданием ивента

            viewModel.createEvent(Event(

            ))
        }
    }

    private fun observeViewModel() {
        viewModel.eventList.observe(viewLifecycleOwner){
            eventAdapter.submitList(it)
            eventAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecycler() = with(binding) {
        recycler.adapter = eventAdapter
    }
}